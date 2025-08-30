package com.axuanhogan.adapter.`in`.gateway.v1.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.util.UUID

interface UserResourceResponse {

    data class GetUser(
        @field:JsonProperty("id")
        @field:Schema(required = true)
        val id: UUID,

        @field:JsonProperty("email")
        @field:Schema(required = true)
        val email: String,

        @field:JsonProperty("name")
        @field:Schema(required = true)
        val name: String,
    )
}