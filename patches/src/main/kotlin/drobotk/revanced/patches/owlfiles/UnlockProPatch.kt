package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Unlock Pro",
) {
    compatibleWith("com.skyjos.apps.fileexplorerfree")

    execute {
        checkForProLicenseFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1
                return v0
            """,
        )
    }
}
