package com.axuanhogan.adapter.`in`.gateway.v1.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema

interface UserResourceResponse {

    data class GetUser(
        @field:JsonProperty("email")
        @field:Schema(required = true)
        val email: String,
    )

    data class CreateUser(
        @field:JsonProperty("message")
        @field:Schema(required = true)
        val message: String,
    )
}
