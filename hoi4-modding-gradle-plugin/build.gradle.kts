plugins {
    kotlin("jvm") version "1.6.10"
    id("java-gradle-plugin")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
    implementation("io.github.classgraph:classgraph:4.8.138")

    implementation(project(":hoi4-java-modding"))
}

tasks.jar {
    archiveBaseName.set("hoi4-modding-gradle-plugin")
}
