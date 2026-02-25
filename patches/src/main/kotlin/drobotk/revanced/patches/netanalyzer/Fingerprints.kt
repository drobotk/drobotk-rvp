package drobotk.revanced.patches.netanalyzer

import app.revanced.patcher.fingerprint

internal val deobfStringFingerprint = fingerprint {
    parameters("Ljava/lang/String;")
    returns("Ljava/lang/String;")
    custom { _, c ->
        c.type.startsWith("Lnet/techet/netanalyzershared/util")
    }
}

internal val whateverFingerprint = fingerprint {
    custom { m, c ->
        m.name == "run" && c.type == "Lo/Fr;"
    }
}

internal val whatever2Fingerprint = fingerprint {
    strings("QW< ekVJi ou(fat a(eo")  // is_tampered
}

internal val whatever3Fingerprint = fingerprint {
    strings("ZL? 5dLDOz wHw AK4CA7e KPl MWuS25 )I")  // android.app.Activity
}
