import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.springframework.boot.gradle.tasks.bundling.BootJar


plugins {
    idea
    id("com.google.protobuf") version "0.8.11"
}

tasks {
    "jar"(Jar::class) {
        enabled = true
    }

    "bootJar"(BootJar::class) {
        enabled = false
    }
}
dependencies {
    implementation("com.google.protobuf:protobuf-java:3.11.1")
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("src/main/java"))
        sourceDirs.add(file("src/main/java"))
    }
}

protobuf {
    // Configure the protoc executable
    protoc {
        // Download from repositories
        artifact = "com.google.protobuf:protoc:3.11.1"
    }

    generatedFilesBaseDir = "$projectDir/src"

    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.doFirst {
                delete("$generatedFilesBaseDir/main/java")
            }
        }
    }
}
