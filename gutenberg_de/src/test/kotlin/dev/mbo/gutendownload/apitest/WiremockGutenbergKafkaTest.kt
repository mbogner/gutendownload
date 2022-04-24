package dev.mbo.gutendownload.apitest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

open class WiremockGutenbergKafkaTest {

    @Test
    fun testApiMock() {
        assertThat(apiMock.port()).isNotNull
        assertThat(apiMock.url()).matches(Pattern.compile("http://localhost:\\d+"))
    }

    companion object {
        val apiMock = ApiMockGutenberg(index = true, chapters = true)

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            apiMock.start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            apiMock.stop()
        }
    }
}