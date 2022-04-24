package dev.mbo.gutendownload.util

import java.io.File

class FileReader {

    companion object {
        fun readFromClasspath(cp: String): String {
            return FileReader::class.java.getResource(cp).readText()
        }

        fun readFromDisk(path: String): String {
            return File(path).readText()
        }
    }

}