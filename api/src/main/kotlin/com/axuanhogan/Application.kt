package com.axuanhogan

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Application
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info

@ApplicationScoped
@OpenAPIDefinition(
    info = Info(
        title = "API Services",
        version = "1.0.0"
    )
)
class Application : Application() {
}
