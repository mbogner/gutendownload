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
import dev.mbo.gutendownload.downloader.model.Replacement
import dev.mbo.gutendownload.util.PageParser
import dev.mbo.gutendownload.util.TextExtractor
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern

@Component
class IndexParser(
    private val downloader: SinglePageDownloader,
) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    suspend fun parseIndex(path: String, indexHtml: String, encoding: Charset, indexConfig: IndexConfig): List<Page> {
        val httpResponse = downloader.download("$path/$indexHtml")
        StatusCodeComparator.compare(httpResponse.status, HttpStatusCode.OK)
        log.debug("downloaded index from {}/{}", path, indexHtml)
        val body = TextExtractor.extractPart(
            text = httpResponse.bodyAsText(encoding),
            windowStart = indexConfig.windowStart,
            windowEnd = indexConfig.windowEnd,
            detailWindowStart = indexConfig.detailWindowStart,
            detailWindowEnd = indexConfig.detailWindowEnd,
            replacements = indexConfig.replacements
        )
        val pages = PageParser.readIntoPages(
            lines = TextExtractor.split(text = body),
            regex = indexConfig.lineRegex,
            pathIndex = indexConfig.linePathIndex,
            nameIndex = indexConfig.lineNameIndex,
        )
        log.debug("read {} pages", pages.size)
        return pages
    }

}