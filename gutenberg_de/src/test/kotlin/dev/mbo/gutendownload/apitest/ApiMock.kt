package dev.mbo.gutendownload.apitest

import com.fasterxml.jackson.core.JsonProcessingException
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import com.github.tomakehurst.wiremock.http.HttpHeader
import com.github.tomakehurst.wiremock.http.HttpHeaders
import com.github.tomakehurst.wiremock.http.MimeType
import dev.mbo.gutendownload.util.FileReader
import io.ktor.http.HttpStatusCode
import org.assertj.core.api.Assertions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
abstract class ApiMock {

    private val log: Logger = LoggerFactory.getLogger(javaClass)
    protected val host = "localhost"
    protected var port: Int? = null
    protected var wireMockServer: WireMockServer? = null

    open fun start() {
        log.info("starting wiremock server")
        try {
            startWiremock()
            customConfigs()
            stub404()
            log.info("started wiremock server on port ${port()}")
        } catch (exc: JsonProcessingException) {
            throw IllegalStateException(exc)
        }
    }

    protected abstract fun customConfigs()

    open fun stop() {
        wireMockServer!!.stop()
        wireMockServer = null
        port = null
        log.info("stopped wiremock server")
    }

    protected open fun startWiremock() {
        wireMockServer = WireMockServer(wiremockConfiguration())
        wireMockServer!!.start()
        port = wireMockServer!!.port()
        WireMock.configureFor(host, port!!)
    }

    open fun port(): Int {
        if(null == port) {
            throw IllegalStateException("not started")
        }
        return port!!
    }

    open fun url(): String {
        return "http://$host:${port()}"
    }

    protected open fun wiremockConfiguration(): WireMockConfiguration {
        return WireMockConfiguration.wireMockConfig()
            .port(0)
            .disableRequestJournal()
            .extensions(ResponseTemplateTransformer(false))
    }

    protected open fun stub404() {
        WireMock.stubFor(
            WireMock.any(WireMock.anyUrl())
                .atPriority(Int.MAX_VALUE)
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(404)
                        .withHeaders(
                            HttpHeaders(
                                HttpHeader.httpHeader(
                                    io.ktor.http.HttpHeaders.ContentType,
                                    MimeType.PLAIN.toString()
                                )
                            )
                        )
                        .withBody("404 not found")
                )
        )
    }

    protected open fun get(
        url: String,
        baseDir: String = "/wiremock",
        status: HttpStatusCode = HttpStatusCode.OK
    ) {
        log.info("mocking GET $url with status $status")
        Assertions.assertThat(url).startsWith(File.separator)
        val content = FileReader.readFromClasspath("$baseDir$url")
        Assertions.assertThat(content).isNotBlank

        WireMock.stubFor(
            WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(status.value)
                        .withBody(content)
                )
        )
    }

}