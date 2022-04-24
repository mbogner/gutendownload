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