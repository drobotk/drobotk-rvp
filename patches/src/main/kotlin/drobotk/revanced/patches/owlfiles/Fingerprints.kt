package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.fingerprint

internal val checkForLicenseFingerprint = fingerprint {
    returns("Z")
    strings("OWLFILES_LICENSE_KEY")
}