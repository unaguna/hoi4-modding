plugins {
    kotlin("jvm") version "1.6.10"
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
}

tasks.jar {
    archiveBaseName.set("hoi4-java-modding")
}

tasks.test {
    useJUnitPlatform()
}
