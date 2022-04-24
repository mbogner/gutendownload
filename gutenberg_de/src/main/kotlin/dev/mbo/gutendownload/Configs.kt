package dev.mbo.gutendownload

import dev.mbo.gutendownload.downloader.model.IndexConfig
import dev.mbo.gutendownload.downloader.model.PageConfig
import dev.mbo.gutendownload.downloader.model.Replacement
import java.util.regex.Pattern

class Configs {

    companion object {
        val INDEX_CONFIG = IndexConfig(
            windowStart = "Inhaltsverzeichnis",
            windowEnd = "</p>",
            detailWindowStart = "<ul>",
            detailWindowEnd = "</ul>",
            replacements = listOf(Replacement("&nbsp;", " ")),
            lineRegex = Pattern.compile(".*href=\"(.*)\">(.*)</a>.*"),
            linePathIndex = 1,
            lineNameIndex = 2,
        )
        val PAGE_CONFIG = PageConfig(
            windowStart = "</hr>",
            windowEnd = "</hr>",
            detailWindowStart = "</h2>",
            detailWindowEnd = "<hr ",
            replacements = emptyList()
        )
    }

}