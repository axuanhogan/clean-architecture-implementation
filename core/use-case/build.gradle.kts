plugins {
    kotlin("jvm") version "2.0.21"
}

group = "personal.my"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core:domain"))
}

kotlin {
    jvmToolchain(21)
}
