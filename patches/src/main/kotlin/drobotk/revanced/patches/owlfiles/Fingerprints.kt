package drobotk.revanced.patches.owlfiles

import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext

internal val BytecodePatchContext.isLicenseKeySetMethod by gettingFirstMethodDeclaratively {
    returnType("Z")
    strings("OWLFILES_LICENSE_KEY")
}
