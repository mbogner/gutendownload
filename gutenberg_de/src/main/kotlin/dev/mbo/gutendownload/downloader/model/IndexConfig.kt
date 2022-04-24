package dev.mbo.gutendownload.downloader.model

import java.util.regex.Pattern

data class IndexConfig(
    val windowStart: String?,
    val windowEnd: String?,
    val detailWindowStart: String?,
    val detailWindowEnd: String?,
    val replacements: List<Replacement>,

    val lineRegex: Pattern,
    val linePathIndex: Int,
    val lineNameIndex: Int,
)