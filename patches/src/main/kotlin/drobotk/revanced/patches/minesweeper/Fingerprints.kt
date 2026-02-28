package drobotk.revanced.patches.minesweeper

import app.revanced.patcher.gettingFirstMethodDeclaratively
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.returnType
import app.revanced.patcher.strings

internal val BytecodePatchContext.isSubscriptionEnabledMethod by gettingFirstMethodDeclaratively {
    returnType("Z")
    strings("premium_subscr", "premium_year_notrial", "premium_month", "life_time")
}