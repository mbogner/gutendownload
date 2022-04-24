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