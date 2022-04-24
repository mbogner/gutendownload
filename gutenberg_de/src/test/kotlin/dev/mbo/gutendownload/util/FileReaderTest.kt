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