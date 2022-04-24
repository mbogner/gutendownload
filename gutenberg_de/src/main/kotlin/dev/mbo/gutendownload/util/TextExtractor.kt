package dev.mbo.gutendownload.util

import dev.mbo.gutendownload.downloader.model.Page
import dev.mbo.gutendownload.downloader.model.Replacement
import dev.mbo.gutendownload.fromTillEndOf
import java.util.regex.Pattern

class TextExtractor {

    companion object {
        fun extractPart(
            text: String,
            windowStart: String? = null,
            windowEnd: String? = null,
            detailWindowStart: String? = null,
            detailWindowEnd: String? = null,
            replacements: List<Replacement> = emptyList()
        ): String {
            var lineStr = text
            if (null != windowStart) {
                lineStr = lineStr.fromTillEndOf(windowStart)
            }
            if (null != windowEnd) {
                val index = lineStr.indexOf(windowEnd)
                if (index > -1) lineStr = lineStr.substring(0, index)
            }
            if (null != detailWindowStart) {
                lineStr = lineStr.fromTillEndOf(detailWindowStart)
            }
            if (null != detailWindowEnd) {
                val index = lineStr.indexOf(detailWindowEnd)
                if (index > -1) lineStr = lineStr.substring(0, index)
            }
            return replace(lineStr.trim(), replacements)
        }

        fun split(text: String, splitBy: String = "\n", replacements: List<Replacement> = emptyList()): List<String> {
            val lines = text.split(splitBy).toMutableList()
            for (i in 0 until lines.size) {
                lines[i] = replace(lines[i].trim(), replacements)
            }
            return lines
        }

        private fun replace(orig: String, replacements: List<Replacement> = emptyList()): String {
            var result = orig
            for(replacement in replacements) {
                result = result.replace(Regex(replacement.regex), replacement.with)
            }
            return result
        }

    }

}