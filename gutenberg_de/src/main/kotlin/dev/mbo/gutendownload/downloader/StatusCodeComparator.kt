package dev.mbo.gutendownload.downloader

import dev.mbo.gutendownload.downloader.exc.BadStatusCodeException
import io.ktor.http.HttpStatusCode

class StatusCodeComparator {

    companion object {
        fun compare(received: HttpStatusCode, expected: HttpStatusCode) {
            if (received != expected) {
                throw BadStatusCodeException(received, expected)
            }
        }
    }

}