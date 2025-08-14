package com.axuanhogan.adapter

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseBean private constructor(
) {
    interface ResponseEntity

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class SuccessResponseEntity<T>(
        val data: T,
    ) : ResponseEntity

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class ErrorResponseEntity(
        val error: Error,
    ) : ResponseEntity {
        class Error(val code: String, val message: String?)
    }

    companion object {
        fun ok(data: Any): Response {
            val entity = SuccessResponseEntity(data = data)
            return Response.status(Status.OK).entity(entity).build()
        }

        fun error(
            status: Status,
            code: String,
            message: String? = "Error",
        ): Response {
            return errorEntity(
                status = status.statusCode,
                reasonPhrase = status.reasonPhrase,
                code = code,
                message = message,
            )
        }

        fun unprocessableEntity(
            code: String,
            message: String? = "Error",
        ): Response {
            return errorEntity(
                status = 422,
                reasonPhrase = "Unprocessable Entity",
                code = code,
                message = message,
            )
        }

        private fun errorEntity(
            status: Int,
            reasonPhrase: String,
            code: String,
            message: String? = "Error",
        ): Response {
            val entity = ErrorResponseEntity(error = ErrorResponseEntity.Error(code = code, message = message))
            return Response.status(status, reasonPhrase).entity(entity).build()
        }
    }
}

