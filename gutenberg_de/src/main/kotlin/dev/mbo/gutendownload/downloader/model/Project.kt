package dev.mbo.gutendownload.downloader.model

import java.nio.charset.Charset

data class Project(
    val name: String,
    val basePath: String,
    val indexFile: String,
    val encoding: Charset = Charsets.UTF_8,
    val indexConfig: IndexConfig,
    val pageConfig: PageConfig,
    val htmlStart: String? = null,
    val htmlEnd: String? = null,
    var pages: List<Page> = emptyList(),
    var content: String? = null
) {

    fun concatenatePageContent(): String {
        content = ""
        for (page in pages) {
            content += page.content
        }
        return content!!
    }

}