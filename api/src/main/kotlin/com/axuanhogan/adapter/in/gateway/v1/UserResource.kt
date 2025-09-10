package com.axuanhogan.adapter.`in`.gateway.v1

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import com.axuanhogan.common.util.ResponseBean
import com.axuanhogan.adapter.`in`.gateway.v1.request.UserResourceRequest
import com.axuanhogan.adapter.`in`.gateway.v1.response.UserResourceResponse
import com.axuanhogan.adapter.security.ResourcePermissionChecker
import com.axuanhogan.common.util.RandomCodeUtil
import com.axuanhogan.core.use_case.UserUseCase
import com.axuanhogan.core.port.`in`.pdo.UserPDO
import io.quarkus.logging.Log
import io.quarkus.security.PermissionsAllowed
import jakarta.ws.rs.core.Response.Status
import java.util.*

@Tag(name = "User")
@Path("/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermissionsAllowed(ResourcePermissionChecker.SCOPE_USER)
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
                            "code": "ERROR_CODE",
                            "message": "ERROR_MESSAGE"
                        }
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
                            "code": "ERROR_CODE",
                            "message": "ERROR_MESSAGE"
                        }
                    }"""
            )
        ]
    )
)
class UserResource(
    private val userUseCase: UserUseCase
) {

    @GET
    @Path("/{userId}")
    @Operation(summary = "Get User")
    @APIResponses(
        APIResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    schema = Schema(
                        type = SchemaType.OBJECT,
                        properties = [
                            SchemaProperty(name = "data", implementation = UserResourceResponse.GetUser::class)
                        ]
                    ),
                    mediaType = MediaType.APPLICATION_JSON,
                )
            ]
        )
    )
    fun getUser(
        @PathParam("userId") userId: String,
    ): Response {
        val user: UserPDO = userUseCase.getUser(id = UUID.fromString(userId))
            ?:  run {
                val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
                return ResponseBean.unprocessableEntity(
                    code = "USER_NOT_FOUND",
                    message = "User not found",
                    trackingCode = trackingCode,
                )
            }

        return ResponseBean.ok(
            data = UserResourceResponse.GetUser(
                id = user.id,
                email = user.email,
                name = user.name,
            )
        )
    }

    @POST
    @Path("/")
    @Operation(summary = "Create User")
    @APIResponses(
        APIResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    schema = Schema(
                        type = SchemaType.OBJECT,
                        properties = [
                            SchemaProperty(name = "data", implementation = UserResourceResponse.CreateUser::class)
                        ]
                    ),
                    mediaType = MediaType.APPLICATION_JSON,
                )
            ]
        )
    )
    fun createUser(
        body: UserResourceRequest.CreateUser,
    ): Response {

        try {
            userUseCase.createUser(
                pdo = UserPDO(
                    id = UUID.randomUUID(),
                    email = body.email,
                    name = body.name,
                )
            )
        } catch (e: Exception) {
            val trackingCode = RandomCodeUtil.gen(length = 8, needTime = false)
            Log.error("Login failed: get Keycloak OIDC Authorization Token failed, trackingCode: $trackingCode", e)
            return ResponseBean.error(
                status = Status.INTERNAL_SERVER_ERROR,
                code = "CREATE_USER_FAILED",
                message = "Create user failed",
                trackingCode = trackingCode,
            )
        }

        return ResponseBean.ok(
            data = UserResourceResponse.CreateUser(
                message = "niceeee"
            )
        )
    }
}
