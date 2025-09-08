package com.axuanhogan

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Application
import org.eclipse.microprofile.config.ConfigProvider
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info

@ApplicationScoped
@OpenAPIDefinition(
    info = Info(
        title = "API Resources",
        version = "1.0.0"
    )
)
class Application : Application() {

    companion object {
        val domainName: String = ConfigProvider.getConfig().getValue(
            "application.domain-name",
            String::class.java
        )
    }

    class ClientSecret() {
        companion object {
            val cleanArchitectureImplementation: String = ConfigProvider.getConfig().getValue(
                "quarkus.rest-client.keycloak-oidc.client-secret",
                String::class.java
            )
        }
    }
}
