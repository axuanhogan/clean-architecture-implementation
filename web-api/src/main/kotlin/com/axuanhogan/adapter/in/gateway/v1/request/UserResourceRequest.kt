package com.axuanhogan.adapter.`in`.gateway.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema

interface UserResourceRequest {

    data class CreateUser(
        @field:JsonProperty("email")
        @field:Schema(required = true)
        val email: String,

        @field:JsonProperty("name")
        @field:Schema(required = true)
        val name: String,
    )
}
