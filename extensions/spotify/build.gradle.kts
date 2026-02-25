extension {
    name = "extensions/drobotk/spotify.rve"
}

android {
    namespace = "drobotk.revanced.extension"
}

dependencies {
    compileOnly(project(":extensions:spotify:stub"))
}
