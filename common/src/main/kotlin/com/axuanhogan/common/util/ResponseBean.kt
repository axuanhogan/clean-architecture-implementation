package com.axuanhogan.common.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.ws.rs.core.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseBean private constructor(
) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class SuccessResponseEntity<T>(
        val data: T,
    )

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class ErrorResponseEntity(
        val error: Error,
        var trackingCode: String? = null
    ) {
        class Error(val code: String, val message: String?)
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class KeycloakOidcErrorResponseEntity(
        @param:JsonProperty("error") val error: String,
        @param:JsonProperty("error_description") val errorDescription: String? = null
    )

    data class Header(
        val name: String,
        val value: String
    )

    companion object {
        fun ok(
            data: Any? = null,
            headers: List<Header> = emptyList(),
        ): Response {
            val entity = SuccessResponseEntity(data = data)
            val response = Response.status(Response.Status.OK).entity(entity)

            if (headers.isNotEmpty()) {
                headers.forEach {
                    response.header(it.name, it.value)
                }
            }

            return response.build()
        }

        fun error(
            status: Response.Status,
            code: String,
            message: String? = "Error",
            trackingCode: String? = null,
        ): Response {
            return errorEntity(
                status = status.statusCode,
                reasonPhrase = status.reasonPhrase,
                code = code,
                message = message,
                trackingCode = trackingCode,
            )
        }

        fun unprocessableEntity(
            code: String,
            message: String? = "Error",
            trackingCode: String? = null,
        ): Response {
            return errorEntity(
                status = 422,
                reasonPhrase = "Unprocessable Entity",
                code = code,
                message = message,
                trackingCode = trackingCode,
            )
        }

        private fun errorEntity(
            status: Int,
            reasonPhrase: String,
            code: String,
            message: String? = "Error",
            trackingCode: String? = null,
        ): Response {
            val entity = ErrorResponseEntity(
                error = ErrorResponseEntity.Error(
                    code = code,
                    message = message,
                ),
                trackingCode = trackingCode,
            )
            return Response.status(status, reasonPhrase).entity(entity).build()
        }
    }
}