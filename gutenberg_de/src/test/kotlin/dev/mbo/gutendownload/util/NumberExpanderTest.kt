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
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NumberExpanderTest {

    @Test
    fun expand() {
        assertThat(NumberExpander.expand(1, 5)).isEqualTo("00001")
        assertThat(NumberExpander.expand(22, 5)).isEqualTo("00022")
        assertThat(NumberExpander.expand(22, 1)).isEqualTo("22")

        Assertions.assertThrows(AssertionError::class.java) { NumberExpander.expand(1, 0) }
        Assertions.assertThrows(AssertionError::class.java) { NumberExpander.expand(1, -1) }
    }

}