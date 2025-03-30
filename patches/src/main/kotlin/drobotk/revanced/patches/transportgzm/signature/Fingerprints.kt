package drobotk.revanced.patches.transportgzm.signature

import app.revanced.patcher.fingerprint

internal val setupHTTPConnectionFingerprint = fingerprint {
    strings("Could not get fingerprint hash for package: ", "X-Android-Cert")
}