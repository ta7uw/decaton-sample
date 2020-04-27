val DECATON_VERSION = "0.0.35"
dependencies {
    implementation(project(":protobuf"))
    implementation("com.linecorp.decaton:decaton-common:$DECATON_VERSION")
    implementation("com.linecorp.decaton:decaton-processor:$DECATON_VERSION")
    implementation("com.linecorp.decaton:decaton-protobuf:$DECATON_VERSION")
    implementation("org.springframework.boot:spring-boot-starter")
}
