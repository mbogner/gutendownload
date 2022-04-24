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

internal class FileReaderTest {

    @Test
    fun readFromClasspath() {
        val content = FileReader.readFromClasspath("/wiremock/kafka/verwandl/index.html")
        assertThat(content).isNotBlank
    }

    @Test
    fun readFromDisk() {
        val content = FileReader.readFromDisk("build.gradle.kts")
        assertThat(content).isNotBlank
    }

}