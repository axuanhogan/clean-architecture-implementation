package personal.my.adapter.`in`.gateway.v1

import com.fasterxml.jackson.annotation.JsonProperty
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
import personal.my.adapter.ResponseBean
import personal.my.use_case.UserUseCase
import personal.my.use_case.port.`in`.pdo.UserPDO
import personal.my.use_case.port.out.repository.UserRepository.*
import java.util.*

@Tag(name = "User")
@Path("/v1/users")
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
    @Operation(summary = "Get user")
    @APIResponses(
        APIResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    schema = Schema(
                        type = SchemaType.OBJECT,
                        properties = [
                            SchemaProperty(name = "data", implementation = GetUserResponse::class)
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

        lateinit var user: UserPDO

        try {
            user = userUseCase.getUser(id = UUID.fromString(userId))
        } catch (e: UserNotFoundException) {
            return ResponseBean.unprocessableEntity(
                code = "USER_NOT_FOUND",
                message = "User not found"
            )
        }

        return ResponseBean.ok(
            data = GetUserResponse(
                id = user.id,
                name = user.name,
            )
        )
    }
}

data class GetUserResponse(
    @field:JsonProperty("id")
    @field:Schema(required = true)
    val id: UUID,

    @field:JsonProperty("name")
    @field:Schema(required = true)
    val name: String,
)
