package com.axuanhogan.common.mapper.exception

import com.axuanhogan.common.exception.KeycloakOidcException
import com.axuanhogan.common.util.ResponseBean
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
            val entity = response.readEntity(ResponseBean.KeycloakOidcErrorResponseEntity::class.java)
            KeycloakOidcException(
                status = response.statusInfo.statusCode,
                reasonPhrase = response.statusInfo.reasonPhrase,
                error = entity.error,
                errorDescription = entity.errorDescription,
            )
        }
    }
}
