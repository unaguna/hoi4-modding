plugins {
    `maven-publish`
    kotlin("jvm") version "1.6.10"
    id("java-gradle-plugin")
    id("com.google.cloud.artifactregistry.gradle-plugin").version("2.1.5")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
    implementation("io.github.classgraph:classgraph:4.8.138")

    implementation(project(":hoi4-java-modding"))
}

tasks {
    jar {
        archiveBaseName.set("hoi4-modding-gradle-plugin")
    }
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
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

gradlePlugin {
    plugins {
        register("hoi4-modding-gradle-plugin") {
            id = "jp.unaguna.hoi4-modding.hoi4-modding-gradle-plugin"
            description = ""
            displayName = "Hoi4 modding gradle Plugin"
            implementationClass = "jp.unaguna.hoi4modding.gradle.Hoi4ModdingPlugin"
        }
    }
}
