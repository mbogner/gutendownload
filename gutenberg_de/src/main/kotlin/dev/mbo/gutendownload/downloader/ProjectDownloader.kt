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

import dev.mbo.gutendownload.downloader.model.Project
import org.springframework.stereotype.Component
import java.io.File

@Component
class ProjectDownloader(
    private val downloader: Downloader,
) {

    suspend fun downloadIntoFile(project: Project, baseDir: File): File {
        val file = File(
            baseDir,
            project.name
                .replace(" ", "_")
                .replace(",", "-")
                    + "-"
                    + System.currentTimeMillis() / 1000
                    + ".html"
        )
        if (file.exists()) throw IllegalStateException("file ${file.absolutePath} already exists")

        project.pages = downloader.download(
            project.basePath,
            project.indexFile,
            project.encoding,
            project.indexConfig,
            project.pageConfig
        )
        val concat = project.concatenatePageContent()

        if (project.htmlStart != null) file.appendText(
            project.htmlStart
                .replace("%%%TITLE%%%", project.name)
                .replace("%%%ENCODING%%%", project.encoding.name().lowercase())
                    + "\n\n",
            project.encoding
        )
        file.appendText(concat, project.encoding)
        if (project.htmlEnd != null) file.appendText("\n\n" + project.htmlEnd, project.encoding)
        return file
    }

}