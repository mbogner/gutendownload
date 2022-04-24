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

import dev.mbo.gutendownload.Configs
import dev.mbo.gutendownload.apitest.WiremockGutenbergKafkaTest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

internal class DownloaderTest : WiremockGutenbergKafkaTest() {

    private val singlePageDownloader = SinglePageDownloader()
    private val downloader = Downloader(IndexParser(singlePageDownloader), singlePageDownloader)

    @Test
    fun download() {
        runBlocking {
            val pages = downloader.download(
                "${apiMock.url()}/kafka/verwandl",
                "index.html",
                StandardCharsets.UTF_8,
                Configs.INDEX_CONFIG,
                Configs.PAGE_CONFIG,
            )
            assertThat(pages).hasSize(19)
            for (page in pages) {
                assertThat(page.content).isNotBlank
            }
        }
    }
}