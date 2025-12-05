package com.axuanhogan.security

import io.quarkus.security.PermissionChecker
import io.quarkus.security.identity.SecurityIdentity
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.ConfigProvider

@ApplicationScoped
class ResourcePermissionChecker {

    companion object {
        val authCookieName: String? = ConfigProvider.getConfig().getValue("mp.jwt.token.cookie", String::class.java)
        private const val CLAIM_NAME = "scope"
        const val SCOPE_USER = "user"
    }

    private fun getScopes(securityIdentity: SecurityIdentity): List<String> {
        return (securityIdentity.principal as DefaultJWTCallerPrincipal).getClaim<String>(CLAIM_NAME).split(" ")
    }

    @PermissionChecker(SCOPE_USER)
    fun checkUser(securityIdentity: SecurityIdentity): Boolean {
        val scopeList = getScopes(securityIdentity = securityIdentity)
        return (SCOPE_USER in scopeList)
    }
}
