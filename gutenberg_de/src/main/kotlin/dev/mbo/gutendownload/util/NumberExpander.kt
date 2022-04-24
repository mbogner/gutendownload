package dev.mbo.gutendownload.util

class NumberExpander {

    companion object {
        fun expand(nr: Int, len: Int, prefix: String = "0", postfix: String = ""): String {
            assert(len > 0)
            var result = "$nr"
            while (result.length < len) {
                result = "$prefix$result$postfix"
            }
            return result
        }
    }

}