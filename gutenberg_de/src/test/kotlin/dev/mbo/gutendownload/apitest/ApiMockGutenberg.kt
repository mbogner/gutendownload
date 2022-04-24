package dev.mbo.gutendownload.apitest

import dev.mbo.gutendownload.util.NumberExpander
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ApiMockGutenberg(
    private val index: Boolean = true,
    private val chapters: Boolean = false,
) : ApiMock() {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun customConfigs() {
        val base = "/kafka/verwandl"
        if (index) get("$base/index.html")
        if (chapters) {
            for (i in 1 until 20) {
                get("$base/verwa${NumberExpander.expand(i, 3)}.html")
            }
        }
    }
}