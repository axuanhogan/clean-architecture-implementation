plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "clean-architecture-implementation"
include("api")
include("jpa")
include("use-case")
include("core")
include("core:domain")
findProject(":core:domain")?.name = "domain"
include("core:use-case")
findProject(":core:use-case")?.name = "use-case"
include("core:port-in")
findProject(":core:port-in")?.name = "port-in"
