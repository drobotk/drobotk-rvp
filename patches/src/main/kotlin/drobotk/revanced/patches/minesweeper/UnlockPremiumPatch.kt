package drobotk.revanced.patches.minesweeper

import app.revanced.patcher.patch.bytecodePatch
import drobotk.revanced.util.returnEarly

@Suppress("unused")
val unlockPremiumPatch = bytecodePatch(
    name = "Unlock Premium",
) {
    compatibleWith("Draziw.Button.Mines")

    execute {
        isSubscriptionEnabledFingerprint.method.returnEarly(true)
    }
}
