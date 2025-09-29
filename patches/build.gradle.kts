group = "drobotk.revanced"

patches {
    about {
        name = "ReVanced Patches"
        description = "Patches for ReVanced"
        source = "git@github.com:drobotk/rvp.git"
        author = "drobotk"
        contact = "drobotk@github.com"
        website = "https://github.com/drobotk/rvp"
        license = "GNU General Public License v3.0"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}
