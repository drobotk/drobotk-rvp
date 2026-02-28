package drobotk.revanced.patches.all

import app.revanced.patcher.patch.resourcePatch
import app.revanced.patcher.patch.stringOption
import org.w3c.dom.Element

@Suppress("unused")
val changeVersionNamePatch = resourcePatch(
    name = "Change version name",
    description = "Changes the version name of the app.",
    use = false,
) {
    val versionName by stringOption(
        name = "Version name",
        description = "The version name to use.",
        required = true,
    )

    apply {
        document("AndroidManifest.xml").use { document ->
            val manifestElement = document.getElementsByTagName("manifest").item(0) as Element
            manifestElement.setAttribute("android:versionName", "$versionName")
        }
    }
}