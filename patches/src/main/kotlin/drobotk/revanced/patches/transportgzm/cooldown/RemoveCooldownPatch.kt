package drobotk.revanced.patches.transportgzm.cooldown

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val removeCooldownPatch = bytecodePatch(
    name = "Remove cooldown",
) {
    compatibleWith("pl.assecods.innompa.m2go")

    execute {
        val match = createCooldownFingerprint.stringMatches!!.firstOrNull { it.string == "ARG_REMAINING_TIME" }
        createCooldownFingerprint.method.addInstructions(
            match!!.index,
            """
                const/4 p1, 0x1
                const/4 p2, 0x1
            """,
        )
    }
}
