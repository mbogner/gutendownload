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