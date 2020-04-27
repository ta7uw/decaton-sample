allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}

plugins {
    java
    `java-library`
    maven
    idea
    id("org.springframework.boot") version "2.2.2.RELEASE" apply false
    id("org.jetbrains.kotlin.jvm") version "1.3.40" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.40" apply false
}

subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven")
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        jvmArgs = listOf("-Duser.timezone=Asia/Tokyo")
    }

    dependencies {
        // lombok
        compileOnly("org.projectlombok:lombok:1.18.4")
        annotationProcessor("org.projectlombok:lombok:1.18.4")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.4")
        testCompileOnly("org.projectlombok:lombok:1.18.4")

        testImplementation("org.assertj:assertj-core:3.12.2")
        testImplementation("org.mockito:mockito-core:3.0.0")

        // Kotlin
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // Protocol Buffers
        implementation("com.google.protobuf:protobuf-java:3.11.1")
    }
}
