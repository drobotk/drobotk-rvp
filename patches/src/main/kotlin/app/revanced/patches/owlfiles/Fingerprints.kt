package app.revanced.patches.owlfiles

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal val checkForProLicenseFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.STATIC)
    returns("Z")
    parameters("Landroid/content/Context;")
    strings("OWLFILES_LICENSE_KEY")
}