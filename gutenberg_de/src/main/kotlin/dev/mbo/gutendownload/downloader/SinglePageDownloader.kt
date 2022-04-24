package dev.mbo.gutendownload.downloader

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.springframework.stereotype.Component

@Component
class SinglePageDownloader {

    suspend fun download(url: String): HttpResponse {
        val client = HttpClient(CIO)
        return client.get(url)
    }

}