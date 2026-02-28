package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.gettingFirstMethodDeclaratively
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.returnType
import app.revanced.patcher.strings

internal val BytecodePatchContext.isLicenseKeySetMethod by gettingFirstMethodDeclaratively {
    returnType("Z")
    strings("OWLFILES_LICENSE_KEY")
}
