package drobotk.revanced.patches.timespirit

import app.revanced.patcher.definingClass
import app.revanced.patcher.gettingFirstMethodDeclaratively
import app.revanced.patcher.name
import app.revanced.patcher.parameterTypes
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.returnType

internal val BytecodePatchContext.mainActivityOnCreateMethod by gettingFirstMethodDeclaratively {
    name("onCreate")
    definingClass("Lcom/mountaindehead/timelapsproject/view/activity/startScreens/StartingExplanationsActivity;")
    returnType("V")
    parameterTypes("Landroid/os/Bundle;")
}