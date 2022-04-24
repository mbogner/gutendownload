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

import dev.mbo.gutendownload.apitest.WiremockGutenbergKafkaTest
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SinglePageDownloaderTest: WiremockGutenbergKafkaTest() {

    @Test
    fun download() {
        runBlocking {
            val response = SinglePageDownloader().download("${apiMock.url()}/kafka/verwandl/index.html")
            assertThat(response).isNotNull
            assertThat(response.status).isEqualTo(HttpStatusCode.OK)
            assertThat(response.bodyAsText()).startsWith("<!DOCTYPE")
        }
    }

}