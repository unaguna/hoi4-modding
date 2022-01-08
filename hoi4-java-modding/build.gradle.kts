plugins {
    kotlin("jvm") version "1.6.10"
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks.jar {
    archiveBaseName.set("hoi4-java-modding")
}
