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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextExtractorTest {

    @Test
    fun extractPart() {
        val extracted = TextExtractor.extractPart(
            "aaaaa\n  Inhalt:\n " +
                    "<ul>\n" +
                    "<li>test1</li>\n" +
                    "<li>test2</li> \n" +
                    "</ul>\n" +
                    "ending...",
            "Inhalt:",
            "ending",
            "<ul>",
            "</ul>"
        )
        assertThat(extracted).isEqualTo("<li>test1</li>\n<li>test2</li>")
    }

}