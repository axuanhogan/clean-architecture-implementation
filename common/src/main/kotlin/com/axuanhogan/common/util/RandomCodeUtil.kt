package com.axuanhogan.common.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Base64

object RandomCodeUtil {

    private const val ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqustuvwxyz0123456789"

    fun gen(length: Int = 16, needTime: Boolean = false): String {

        val code: String = buildString {
            repeat(length) {
                append(ALPHANUMERIC_CHARS.random())
            }
        }.let {
            if (needTime) {
                val now: String = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
                it + now
            } else {
                it
            }
        }

        return Base64.getEncoder().encodeToString(code.toByteArray())
    }
}
