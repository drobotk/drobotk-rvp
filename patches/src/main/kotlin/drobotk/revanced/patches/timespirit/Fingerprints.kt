package drobotk.revanced.patches.timespirit

import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext

internal val BytecodePatchContext.mainActivityOnCreateMethod by gettingFirstMethodDeclaratively {
    name("onCreate")
    definingClass("Lcom/mountaindehead/timelapsproject/view/activity/startScreens/StartingExplanationsActivity;")
    returnType("V")
    parameterTypes("Landroid/os/Bundle;")
}