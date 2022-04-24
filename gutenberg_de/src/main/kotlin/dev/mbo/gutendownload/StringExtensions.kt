package dev.mbo.gutendownload

fun String.indexEndOf(part: String, ignoreCase: Boolean = false): Int =
    this.indexOf(part, ignoreCase = ignoreCase) + part.length

fun String.fromTillEndOf(part: String, ignoreCase: Boolean = false): String =
    this.substring(this.indexEndOf(part, ignoreCase))