package dev.mbo.gutendownload.util

import dev.mbo.gutendownload.downloader.model.Page
import java.util.regex.Pattern

class PageParser {

    companion object {
        fun readIntoPages(lines: List<String>, regex: Pattern, pathIndex: Int, nameIndex: Int): List<Page> {
            val response: MutableList<Page> = ArrayList(lines.size)
            for (line in lines) {
                val matcher = regex.matcher(line)
                if (matcher.matches()) {
                    if (2 == matcher.groupCount()) {
                        response.add(
                            Page(
                                name = matcher.group(nameIndex),
                                path = matcher.group(pathIndex),
                                content = null
                            )
                        )
                    }
                }
            }
            return response
        }
    }

}