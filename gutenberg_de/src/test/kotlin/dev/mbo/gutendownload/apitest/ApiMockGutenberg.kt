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

package dev.mbo.gutendownload.apitest

import dev.mbo.gutendownload.util.NumberExpander
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ApiMockGutenberg(
    private val index: Boolean = true,
    private val chapters: Boolean = false,
) : ApiMock() {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun customConfigs() {
        val base = "/kafka/verwandl"
        if (index) get("$base/index.html")
        if (chapters) {
            for (i in 1 until 20) {
                get("$base/verwa${NumberExpander.expand(i, 3)}.html")
            }
        }
    }
}