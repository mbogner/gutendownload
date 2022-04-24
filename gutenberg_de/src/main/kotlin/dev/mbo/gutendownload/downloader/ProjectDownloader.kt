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