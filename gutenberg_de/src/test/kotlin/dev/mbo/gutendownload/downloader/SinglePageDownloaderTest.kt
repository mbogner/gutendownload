package dev.mbo.gutendownload.downloader

import dev.mbo.gutendownload.apitest.WiremockGutenbergKafkaTest
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SinglePageDownloaderTest: WiremockGutenbergKafkaTest() {

    @Test
    fun download() {
        runBlocking {
            val response = SinglePageDownloader().download("${apiMock.url()}/kafka/verwandl/index.html")
            assertThat(response).isNotNull
            assertThat(response.status).isEqualTo(HttpStatusCode.OK)
            assertThat(response.bodyAsText()).startsWith("<!DOCTYPE")
        }
    }

}