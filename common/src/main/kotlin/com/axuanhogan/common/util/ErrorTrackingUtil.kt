package com.axuanhogan.common.util

import java.security.SecureRandom

object ErrorTrackingUtil {

    private const val ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqustuvwxyz0123456789"
    private val randomIndex = SecureRandom()

    fun genTrackingCode(length: Int = 16): String {
        return (1..length)
            .map { ALPHANUMERIC_CHARS[randomIndex.nextInt(ALPHANUMERIC_CHARS.length)] }
            .joinToString("")
    }
}
