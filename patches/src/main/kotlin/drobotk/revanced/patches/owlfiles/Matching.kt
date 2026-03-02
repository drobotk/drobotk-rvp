package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.gettingFirstMethodDeclaratively
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.returnType

internal val BytecodePatchContext.isLicenseKeySetMethod by gettingFirstMethodDeclaratively("OWLFILES_LICENSE_KEY") {
    returnType("Z")
}
