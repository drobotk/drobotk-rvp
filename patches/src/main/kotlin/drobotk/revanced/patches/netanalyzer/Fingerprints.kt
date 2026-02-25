package drobotk.revanced.patches.netanalyzer

import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext

internal val BytecodePatchContext.deobfStringMethod by gettingFirstMethodDeclaratively {
    parameterTypes("Ljava/lang/String;")
    returnType("Ljava/lang/String;")
    definingClass("Lnet/techet/netanalyzershared/util")
}

internal val BytecodePatchContext.whateverMethod1 by gettingFirstMethodDeclaratively {
    name("run")
    definingClass("Lo/Fr;")
}

internal val BytecodePatchContext.whateverMethod2Match by composingFirstMethod {
    instructions("QW< ekVJi ou(fat a(eo"()) // "is_tampered"
}

internal val BytecodePatchContext.whateverMethod3Match by composingFirstMethod {
    instructions("ZL? 5dLDOz wHw AK4CA7e KPl MWuS25 )I"())  // "android.app.Activity"
}
