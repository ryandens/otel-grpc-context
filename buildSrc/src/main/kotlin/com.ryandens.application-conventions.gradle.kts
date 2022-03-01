plugins {
    application
    id("com.diffplug.spotless")
}


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

