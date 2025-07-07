package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.returnEarly

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Unlock Pro",
) {
    compatibleWith("com.skyjos.apps.fileexplorerfree")

    execute {
        isLicenseKeySetFingerprint.method.returnEarly(true)
    }
}
