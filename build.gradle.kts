plugins {
    kotlin("jvm") version "1.6.10"
    id("java-gradle-plugin")
}

group = "jp.unaguna"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
}

tasks.jar {
    archiveBaseName.set("hoi4-modding-gradle-plugin")
}
