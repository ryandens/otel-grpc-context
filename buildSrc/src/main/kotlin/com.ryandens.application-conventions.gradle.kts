plugins {
    application
    id("com.diffplug.spotless")
    id("com.google.cloud.tools.jib")
}


jib.to.image = "ryandens/${project.name.toLowerCase()}"

spotless {
    kotlinGradle {
        ktlint().userData(mapOf("disabled_rules" to "no-wildcard-imports"))
    }
    java {
        googleJavaFormat()
        targetExclude("build/**/*.java")
    }
}



java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

