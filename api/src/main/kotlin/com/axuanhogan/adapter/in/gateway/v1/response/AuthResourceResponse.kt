package com.axuanhogan.adapter.`in`.gateway.v1.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema

interface AuthResourceResponse {

    data class Login(
        @field:JsonProperty("message")
        @field:Schema(required = true)
        val message: String,
    )
}
