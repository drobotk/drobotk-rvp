package drobotk.revanced.patches.transportgzm

import app.revanced.patcher.fingerprint

internal val createCooldownFingerprint = fingerprint {
    strings("cico_type_not_logged", "ARG_REMAINING_TIME")
}