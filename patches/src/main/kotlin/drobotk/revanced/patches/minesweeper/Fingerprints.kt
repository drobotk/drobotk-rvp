package drobotk.revanced.patches.minesweeper

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal val isSubscriptionEnabledFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.STATIC)
    returns("Z")
    parameters("Landroid/content/Context;")
    strings("premium_subscr", "premium_year_notrial", "premium_month", "life_time")
}