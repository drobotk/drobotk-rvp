package drobotk.revanced.patches.timespirit

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal val mainActivityOnCreateFingerprint = fingerprint {
    returns("V")
    parameters("Landroid/os/Bundle;")
    custom { method, classDef ->
        method.name == "onCreate" && classDef.type == "Lcom/mountaindehead/timelapsproject/view/activity/startScreens/StartingExplanationsActivity;"
    }
}