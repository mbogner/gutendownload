package dev.mbo.gutendownload.downloader

import dev.mbo.gutendownload.Configs
import dev.mbo.gutendownload.apitest.WiremockGutenbergKafkaTest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

open class IndexParserTest : WiremockGutenbergKafkaTest() {

    private val bean = IndexParser(SinglePageDownloader())

    @Test
    fun parseIndex() {
        runBlocking {
            val pages = bean.parseIndex(
                "${apiMock.url()}/kafka/verwandl",
                "index.html",
                StandardCharsets.UTF_8,
                Configs.INDEX_CONFIG
            )
            assertThat(pages).hasSize(19)
            for (page in pages) {
                assertThat(page.name).isNotBlank
                assertThat(page.path).isNotBlank
                assertThat(page.content).isNull()
            }
        }
    }

}