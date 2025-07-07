package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.fingerprint

internal val isLicenseKeySetFingerprint = fingerprint {
    returns("Z")
    strings("OWLFILES_LICENSE_KEY")
}
