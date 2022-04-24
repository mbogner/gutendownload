/*
 * Copyright 2022 mbo.dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.mbo.gutendownload.downloader

import dev.mbo.gutendownload.downloader.model.IndexConfig
import dev.mbo.gutendownload.downloader.model.Page
import dev.mbo.gutendownload.downloader.model.PageConfig
import dev.mbo.gutendownload.util.TextExtractor
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.charset.Charset

@Component
class Downloader(
    private val indexParser: IndexParser,
    private val singlePageDownloader: SinglePageDownloader,
) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    suspend fun download(
        path: String,
        indexHtml: String,
        encoding: Charset,
        indexConfig: IndexConfig,
        pageConfig: PageConfig
    ): List<Page> {
        val pages = indexParser.parseIndex(path, indexHtml, encoding, indexConfig)
        for (page in pages) {
            downloadPage(path, page, encoding, pageConfig)
        }
        log.info("downloaded all {} pages", pages.size)
        return pages
    }

    private suspend fun downloadPage(path: String, page: Page, encoding: Charset, pageConfig: PageConfig) {
        val url = "$path/${page.path}"
        log.debug("download {}", url)
        val httpResponse = singlePageDownloader.download(url)
        StatusCodeComparator.compare(httpResponse.status, HttpStatusCode.OK)

        page.content = TextExtractor.extractPart(
            text = httpResponse.bodyAsText(encoding),
            windowStart = pageConfig.windowStart,
            windowEnd = pageConfig.windowEnd,
            detailWindowStart = pageConfig.detailWindowStart,
            detailWindowEnd = pageConfig.detailWindowEnd,
            replacements = pageConfig.replacements,
        )
    }

}