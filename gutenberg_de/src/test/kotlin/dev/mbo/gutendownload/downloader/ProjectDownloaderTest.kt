package dev.mbo.gutendownload.downloader

import dev.mbo.gutendownload.Configs
import dev.mbo.gutendownload.apitest.WiremockGutenbergKafkaTest
import dev.mbo.gutendownload.downloader.model.Project
import dev.mbo.gutendownload.util.FileReader
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class ProjectDownloaderTest : WiremockGutenbergKafkaTest() {

    private val singlePageDownloader = SinglePageDownloader()
    private val downloader = Downloader(IndexParser(singlePageDownloader), singlePageDownloader)
    private val projectDownloader = ProjectDownloader(downloader)

    @Test
    fun downloadIntoFile() {
        runBlocking {
            val file = projectDownloader.downloadIntoFile(
                Project(
                    name = "Franz Kafka,Die Verwandlung",
                    basePath = "${apiMock.url()}/kafka/verwandl",
                    indexFile = "index.html",
                    indexConfig = Configs.INDEX_CONFIG,
                    pageConfig = Configs.PAGE_CONFIG,
                    htmlStart = FileReader.readFromClasspath("/html/1_pre.html"),
                    htmlEnd = FileReader.readFromClasspath("/html/1_post.html"),
                ),
                File("./build")
            )
            assertThat(file).exists()
            val content = FileReader.readFromDisk(file.absolutePath)
            assertThat(content).isNotBlank
        }
    }
}