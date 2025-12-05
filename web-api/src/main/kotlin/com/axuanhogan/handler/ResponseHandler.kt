package com.axuanhogan.handler

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseHandler private constructor(
) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class SuccessResponse<T>(
        val data: T,
    )

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class ErrorResponse(
        val error: Error,
        var trackingCode: String? = null
    ) {
        class Error(
            val code: String,
            val message: String?
        )
    }

    data class Header(
        val name: String,
        val value: String
    )

    companion object {
        fun ok(
            data: Any? = null,
            headers: List<Header> = emptyList(),
            cookie: NewCookie? = null,
        ): Response {
            val entity = SuccessResponse(data = data)
            val response = Response.status(Response.Status.OK).entity(entity)

            if (headers.isNotEmpty()) {
                headers.forEach {
                    response.header(it.name, it.value)
                }
            }

            if (cookie != null) {
                response.cookie(cookie)
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
            val entity = ErrorResponse(
                error = ErrorResponse.Error(
                    code = code,
                    message = message,
                ),
                trackingCode = trackingCode,
            )
            return Response.status(status, reasonPhrase).entity(entity).build()
        }
    }
}