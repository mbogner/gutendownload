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

package dev.mbo.gutendownload.util

import dev.mbo.gutendownload.downloader.model.Page
import java.util.regex.Pattern

class PageParser {

    companion object {
        fun readIntoPages(lines: List<String>, regex: Pattern, pathIndex: Int, nameIndex: Int): List<Page> {
            val response: MutableList<Page> = ArrayList(lines.size)
            for (line in lines) {
                val matcher = regex.matcher(line)
                if (matcher.matches()) {
                    if (2 == matcher.groupCount()) {
                        response.add(
                            Page(
                                name = matcher.group(nameIndex),
                                path = matcher.group(pathIndex),
                                content = null
                            )
                        )
                    }
                }
            }
            return response
        }
    }

}