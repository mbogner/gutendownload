package dev.mbo.gutendownload.downloader.model

data class Page(
    val name: String,
    val path: String,
    var content: String?,
)
