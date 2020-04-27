rootProject.name = "decaton-sample"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        jcenter()
    }
}

include("client")
include("processor")
include("protobuf")
include("common")
