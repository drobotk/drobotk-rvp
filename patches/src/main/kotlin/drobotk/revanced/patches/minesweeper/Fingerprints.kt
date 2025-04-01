package drobotk.revanced.patches.minesweeper

import app.revanced.patcher.fingerprint

internal val isSubscriptionEnabledFingerprint = fingerprint {
    returns("Z")
    strings("premium_subscr", "premium_year_notrial", "premium_month", "life_time")
}