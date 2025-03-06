package drobotk.revanced.patches.timespirit

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Unlock Pro",
) {
    compatibleWith("com.mountaindehead.timelapsproject")

    execute {
        mainActivityOnCreateFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1

                invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

                move-result-object v0

                sput-object v0, Lcom/mountaindehead/timelapsproject/preferences/AppPreferences;->IS_VIP:Ljava/lang/Boolean;
            """,
        )
    }
}
