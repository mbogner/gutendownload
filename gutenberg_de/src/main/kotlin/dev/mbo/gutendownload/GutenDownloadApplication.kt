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

package dev.mbo.gutendownload

import dev.mbo.gutendownload.downloader.ProjectDownloader
import dev.mbo.gutendownload.downloader.model.Project
import dev.mbo.gutendownload.util.FileReader
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class GutenDownloadApplication(
    private val projectDownloader: ProjectDownloader,
    @Value("\${application.commandline-runner.enabled}") private val enabled: Boolean,
) : CommandLineRunner {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        if (enabled) {
            runBlocking {
                projectDownloader.downloadIntoFile(
                    Project(
                        name = "Franz Kafka,Die Verwandlung",
                        basePath = "https://www.projekt-gutenberg.org/kafka/verwandl",
                        indexFile = "index.html",
                        indexConfig = Configs.INDEX_CONFIG,
                        pageConfig = Configs.PAGE_CONFIG,
                        htmlStart = FileReader.readFromClasspath("/html/1_pre.html"),
                        htmlEnd = FileReader.readFromClasspath("/html/1_post.html"),
                    ),
                    File(".")
                )
            }
        } else {
            log.warn("disabled")
        }
    }

}

fun main(args: Array<String>) {
    runApplication<GutenDownloadApplication>(*args)
}
