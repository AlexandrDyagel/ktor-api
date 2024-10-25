package com.example.utils

import io.ktor.util.*
import java.net.URLDecoder
import java.nio.charset.Charset

class TelegramDataVerifier(private val initData: String) {

    private val WEB_APP_DATA_STRING = "WebAppData"

    fun verify(): Boolean {
        val dataCheckString = dataCheckString()
        val secretKey = secretKey()
        return hex(hmacSHA256(dataCheckString, secretKey)) ==
    }

    private fun secretKey(): String {
        return hmacSHA256(System.getenv("TELEGRAM_BOT_TOKEN"), WEB_APP_DATA_STRING)
    }

    private fun dataCheckString(): String {
        return keyValueStrings().joinToString("\n")
    }

    private fun keyValueStrings() : List<String> {
        val decodeString = decodeUrlString(initData).split("\":\"", limit = 2)[1].dropLast(2)
        return decodeString.split("&").sorted()
    }

    private fun decodeUrlString(initData: String): String {
        return URLDecoder.decode(initData, Charset.forName("UTF-8"))
    }

}

