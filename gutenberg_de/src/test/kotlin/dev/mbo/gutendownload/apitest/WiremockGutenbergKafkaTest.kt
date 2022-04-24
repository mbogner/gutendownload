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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

open class WiremockGutenbergKafkaTest {

    @Test
    fun testApiMock() {
        assertThat(apiMock.port()).isNotNull
        assertThat(apiMock.url()).matches(Pattern.compile("http://localhost:\\d+"))
    }

    companion object {
        val apiMock = ApiMockGutenberg(index = true, chapters = true)

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            apiMock.start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            apiMock.stop()
        }
    }
}