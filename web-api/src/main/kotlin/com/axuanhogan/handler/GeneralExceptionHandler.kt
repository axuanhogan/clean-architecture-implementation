package com.axuanhogan.handler

import com.axuanhogan.common.util.RandomCodeUtil
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GeneralExceptionHandler : ExceptionMapper<Exception> {
    override fun toResponse(exception: Exception): Response {

        val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
        Log.error("[$trackingCode] Catch the Exception in GeneralExceptionHandler", exception)
        return ResponseHandler.error(
            status = Response.Status.INTERNAL_SERVER_ERROR,
            code = "UNDEFINED_ERROR",
            trackingCode = trackingCode,
        )
    }
}
