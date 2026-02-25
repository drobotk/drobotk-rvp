rootProject.name = "drobotk-rvp"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/revanced/revanced-patches-template")
            credentials(PasswordCredentials::class)
        }
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
    }
}

plugins {
    id("app.revanced.patches") version "1.0.0-dev.9"
}
