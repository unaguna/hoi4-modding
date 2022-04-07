plugins {
    `maven-publish`
    kotlin("jvm") version "1.6.10"
    id("com.google.cloud.artifactregistry.gradle-plugin").version("2.1.5")
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
            setUrl("artifactregistry://asia-northeast1-maven.pkg.dev/hoi4-346504/hoi4-modding-maven")
        }
    }
}
