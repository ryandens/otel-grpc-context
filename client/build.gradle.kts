plugins {
    id("com.ryandens.application-conventions")
}
repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val grpcVersion = "1.44.1"

application {
    mainClass.set("com.ryandens.grpc.Client")
}

dependencies {
    implementation(project(":app"))
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
}
