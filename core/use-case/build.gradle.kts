plugins {
    kotlin("jvm") version "2.0.21"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(project(":core:domain"))
}
