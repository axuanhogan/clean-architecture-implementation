package com.axuanhogan.common.mapper

import com.axuanhogan.common.exception.KeycloakOidcException
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.ws.rs.core.MultivaluedMap
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper
import kotlin.jvm.java

class KeycloakOidcExceptionMapper : ResponseExceptionMapper<KeycloakOidcException> {

    override fun handles(status: Int, headers: MultivaluedMap<String?, Any?>?): Boolean {
        return status >= 400
    }

    override fun toThrowable(response: Response): KeycloakOidcException? {
        return if (response.status == Response.Status.OK.statusCode) {
            null
        } else {
            val entity = response.readEntity(KeycloakOidcErrorResponseEntity::class.java)
            KeycloakOidcException(
                status = response.statusInfo.statusCode,
                reasonPhrase = response.statusInfo.reasonPhrase,
                error = entity.error,
                errorDescription = entity.errorDescription,
            )
        }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class KeycloakOidcErrorResponseEntity(
    @param:JsonProperty("error") val error: String,
    @param:JsonProperty("error_description") val errorDescription: String? = null
)
