plugins {
    `maven-publish`
    kotlin("jvm") version "1.6.10"
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
}

tasks {
    jar {
        archiveBaseName.set("hoi4-java-modding")
    }
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
        }
    }

    repositories {
        maven {
            setUrl("gcs://unaguna-hoi4-maven/maven")
        }
    }
}
