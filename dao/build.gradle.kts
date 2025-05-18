plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("plugin.allopen") version "2.0.21"
    id("io.quarkus") version "3.6.9"
}

group = "personal.my"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.quarkus:quarkus-extension-processor:3.6.9")

    testImplementation(kotlin("test"))

    implementation(enforcedPlatform("io.quarkus:quarkus-bom:3.6.9"))

    implementation("io.quarkus:quarkus-spring-data-jpa")

    implementation("io.quarkus:quarkus-jdbc-postgresql")

    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-hibernate-validator")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}
