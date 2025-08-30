plugins {
    id("io.quarkus") version "3.20.2.1" apply false
    kotlin("jvm") version "2.2.0" apply false
    kotlin("plugin.allopen") version "2.2.0" apply false
    kotlin("plugin.jpa") version "2.2.0" apply false
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    group = "com.axuanhogan"
    version = "1.0-SNAPSHOT"
}
