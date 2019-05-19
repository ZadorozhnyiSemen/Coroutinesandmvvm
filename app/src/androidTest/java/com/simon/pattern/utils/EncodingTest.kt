package com.simon.pattern.utils

import android.util.Base64
import org.junit.Test

class EncodingTest {

    @Test
    fun encodingTest() {
        val code = "TmF0aXZlNWVjcmV0UEBzc3cwcmQy"
        val bytes = Base64.decode(code, Base64.DEFAULT)
        val decode = String(bytes)
        println("=========== " + decode)

        val codeBack = String(Base64.encode(bytes, Base64.DEFAULT))
        println("---------->>> $codeBack")

        val clientId = "http://replay-app-login/callback"
        println("original key: $clientId")
        val encodedClientId = String(Base64.encode(clientId.toByteArray(), Base64.DEFAULT))
        println("encoded key: $encodedClientId")
        val decodedClientId = String(Base64.decode(encodedClientId, Base64.DEFAULT))
        println("decoded key: $decodedClientId")
    }
}