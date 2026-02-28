extension {
    name = "extensions/drobotk/spotify.rve"
}

android {
    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    compileOnly(project(":extensions:spotify:stub"))
}
