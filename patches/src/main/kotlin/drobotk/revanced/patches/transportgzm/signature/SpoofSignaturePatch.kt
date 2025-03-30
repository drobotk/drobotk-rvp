package drobotk.revanced.patches.transportgzm.signature

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val spoofSignaturePatch = bytecodePatch(
    name = "Spoof signature",
) {
    compatibleWith("pl.assecods.innompa.m2go")

    execute {
        val match = setupHTTPConnectionFingerprint.stringMatches!!.firstOrNull { it.string == "X-Android-Cert" }
        setupHTTPConnectionFingerprint.method.addInstructions(
            match!!.index + 1,
            """
                const-string v0, "2BEFFBFDE92A6FC717788D1D2ADA1EE1161726FF"
            """,
        )
    }
}
