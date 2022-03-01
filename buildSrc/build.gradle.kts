plugins  {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.3.0")
    implementation("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:3.2.0")
    implementation("com.ryandens:plugin:0.2.2")
}