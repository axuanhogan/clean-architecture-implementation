package com.axuanhogan.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.quarkus.logging.Log
import java.util.Base64
import kotlin.text.split

object JwtTokenUtil {

    private val objectMapper = ObjectMapper()

    fun parseTokenPayload(token: String): Map<String, Any>? {
        return try {
            val parts = token.split(".")
            if (parts.size != 3) {
                Log.warn("Invalid JWT token format: expected 3 parts but got ${parts.size}")
                return null
            }

            val payload = parts[1]
            val decodedPayload = Base64.getUrlDecoder().decode(payload)
            val payloadString = String(decodedPayload)

            objectMapper.readValue<Map<String, Any>>(payloadString)
        } catch (e: Exception) {
            Log.error("Failed to parse JWT token payload", e)
            null
        }
    }

    fun extractTokenClaim(token: String, claimName: String): String? {
        val payload = parseTokenPayload(token)
        return payload?.get(claimName) as? String
    }
}
