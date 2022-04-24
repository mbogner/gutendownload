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