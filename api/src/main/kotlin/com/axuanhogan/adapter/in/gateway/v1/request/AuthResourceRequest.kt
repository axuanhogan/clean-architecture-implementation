package com.axuanhogan.adapter.`in`.gateway.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema

interface AuthResourceRequest {

    data class Login(
        @field:JsonProperty("email")
        @field:Schema(required = true)
        val email: String,

        @field:JsonProperty("password")
        @field:Schema(required = true)
        val password: String,
    )
}
