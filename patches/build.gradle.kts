group = "drobotk.revanced"

patches {
    about {
        name = "DRoboTK ReVanced Patches"
        description = "Patches for ReVanced"
        source = "git@github.com:drobotk/rvp.git"
        author = "drobotk"
        license = "GNU General Public License v3.0"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}