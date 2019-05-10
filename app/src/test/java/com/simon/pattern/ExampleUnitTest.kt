package com.simon.pattern

import com.simon.pattern.utils.urlEncode
import org.junit.Test
import java.net.URLDecoder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun encodingTest() {
        val text = "Yeah Yeah Yeah!"

        println(URLDecoder.decode(text, "UTF-8"))
        print(text.urlEncode())
    }
}
