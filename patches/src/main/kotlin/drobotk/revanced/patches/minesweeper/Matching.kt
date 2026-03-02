package drobotk.revanced.patches.minesweeper

import app.revanced.patcher.gettingFirstMethodDeclaratively
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.returnType

internal val BytecodePatchContext.isSubscriptionEnabledMethod by gettingFirstMethodDeclaratively("premium_subscr", "premium_year_notrial", "premium_month", "life_time") {
    returnType("Z")
}