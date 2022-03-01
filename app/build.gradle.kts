import com.google.protobuf.gradle.*

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.google.protobuf") version "0.8.17"
    id("com.ryandens.javaagent-application") version "0.2.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}
val grpcVersion = "1.44.1"
val protocVersion = "3.19.2"

dependencies {
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    runtimeOnly("io.grpc:grpc-netty-shaded:${grpcVersion}")
    javaagent("io.opentelemetry.javaagent:opentelemetry-javaagent:1.11.1")
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
}


protobuf {
    protoc {
        this.artifact = "com.google.protobuf:protoc:${protocVersion}"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc") {
                }
            }
        }
    }

}

// Inform IDEs like IntelliJ IDEA, Eclipse or NetBeans about the generated code.
sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}

application {
    // Define the main class for the application.
    mainClass.set("com.ryandens.otel.grpc.App")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
