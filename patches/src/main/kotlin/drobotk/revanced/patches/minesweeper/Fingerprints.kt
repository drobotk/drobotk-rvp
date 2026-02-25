package drobotk.revanced.patches.minesweeper

import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext

internal val BytecodePatchContext.isSubscriptionEnabledMethod by gettingFirstMethodDeclaratively {
    returnType("Z")
    strings("premium_subscr", "premium_year_notrial", "premium_month", "life_time")
}