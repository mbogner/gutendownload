package dev.mbo.gutendownload.downloader.model

data class PageConfig(
    val windowStart: String?,
    val windowEnd: String?,
    val detailWindowStart: String?,
    val detailWindowEnd: String?,
    val replacements: List<Replacement>,
)