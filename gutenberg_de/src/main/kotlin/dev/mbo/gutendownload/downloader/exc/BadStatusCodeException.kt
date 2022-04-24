package dev.mbo.gutendownload.downloader.exc

import io.ktor.http.HttpStatusCode

class BadStatusCodeException(
    received: HttpStatusCode,
    expected: HttpStatusCode,
) : RuntimeException("received status code $received but expected $expected")