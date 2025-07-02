plugins {
    kotlin("jvm") version "2.0.21"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
        javaParameters = true
    }
}
