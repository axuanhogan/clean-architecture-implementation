package com.axuanhogan.adapter.`in`.gateway.v1

import com.axuanhogan.common.util.ResponseBean
import com.axuanhogan.common.exception.KeycloakOidcException
import com.axuanhogan.adapter.`in`.gateway.v1.request.AuthResourceRequest
import com.axuanhogan.adapter.`in`.gateway.v1.response.AuthResourceResponse
import com.axuanhogan.adapter.security.ResourcePermissionChecker
import com.axuanhogan.adapter.security.ResourcePermissionChecker.Companion.SCOPE_USER
import com.axuanhogan.common.client.KeycloakOidcClient
import com.axuanhogan.common.util.RandomCodeUtil
import com.axuanhogan.core.use_case.auth.GetTokenByPasswordGrantUseCase
import com.axuanhogan.core.use_case.auth.GetTokenByPasswordGrantUseCaseInput
import io.quarkus.logging.Log
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Auth")
@Path("/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@APIResponses(
    APIResponse(
        responseCode = "422",
        description = "Unprocessable Entity",
        content = [
            Content(
                schema = Schema(implementation = ResponseBean.ErrorResponseEntity::class),
                mediaType = MediaType.APPLICATION_JSON,
                example = """{
                        "error": {
                            "code": "ERROR",
                            "message": "ERROR_MESSAGE"
                        },
                        "extra": null
                    }"""
            )
        ]
    ),
    APIResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = [
            Content(
                schema = Schema(implementation = ResponseBean.ErrorResponseEntity::class),
                mediaType = MediaType.APPLICATION_JSON,
                example = """{
                        "error": {
                            "code": "ERROR",
                            "message": "ERROR_MESSAGE"
                        },
                        "extra": null
                    }"""
            )
        ]
    )
)
class AuthResource (
    @param:ConfigProperty(name = "quarkus.rest-client.keycloak-oidc.client-secret") val clientSecret: String,
    @param:ConfigProperty(name = "application.domain-name") val domainName: String,
    private val getTokenByPasswordGrantUseCase: GetTokenByPasswordGrantUseCase
) {

    @POST
    @Path("/sign-in")
    @Operation(summary = "登入")
    @APIResponses(
        APIResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    schema = Schema(
                        type = SchemaType.OBJECT,
                        properties = [
                            SchemaProperty(
                                name = "data",
                                implementation = AuthResourceResponse.SignIn::class
                            )
                        ]
                    ),
                    mediaType = MediaType.APPLICATION_JSON,
                )
            ]
        )
    )
    fun signIn(
        body: AuthResourceRequest.SignIn
    ) : Response {

        try {
            val token = getTokenByPasswordGrantUseCase.execute(
                input = GetTokenByPasswordGrantUseCaseInput(
                    clientId = KeycloakOidcClient.Client.CLEAN_ARCHITECTURE_IMPLEMENTATION.name,
                    clientSecret = clientSecret,
                    username = body.email,
                    password = body.password,
                    scope = SCOPE_USER,
                )
            )

            return ResponseBean.ok(
                data = AuthResourceResponse.SignIn(
                    message = "niceeee"
                ),
                headers = listOf(
                    ResponseBean.Header(
                        name = "Set-Cookie",
                        value = "${ResourcePermissionChecker.authCookieName}=$token; Path=/; Secure=true; SameSite=Lax; HttpOnly=true; Domain=$domainName"
                    )
                )
            )
        } catch (e: KeycloakOidcException) {
            val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
            Log.error("Sign in failed: get Keycloak OIDC Authorization Token failed, trackingCode: $trackingCode", e)
            return ResponseBean.error(
                status = Status.INTERNAL_SERVER_ERROR,
                code = "SIGN_IN_FAILED",
                message = "Sign in failed",
                trackingCode = trackingCode,
            )
        } catch (e: Exception) {
            val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
            Log.error("Sign in failed, trackingCode: $trackingCode", e)
            return ResponseBean.error(
                status = Status.INTERNAL_SERVER_ERROR,
                code = "SIGN_IN_FAILED",
                message = "Sign in failed",
                trackingCode = trackingCode,
            )
        }
    }
}
