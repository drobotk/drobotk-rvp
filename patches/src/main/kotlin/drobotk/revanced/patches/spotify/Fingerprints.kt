package drobotk.revanced.patches.spotify

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.iface.ClassDef
import com.android.tools.smali.dexlib2.iface.Method
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import drobotk.revanced.util.getReference
import drobotk.revanced.util.indexOfFirstInstruction

internal fun hasReferenceToGetAssignedProperty(method: Method, classDef: ClassDef) =
    method.indexOfFirstInstruction {
        getReference<MethodReference>()?.let { reference ->
            reference.definingClass == classDef.type
                    && reference.parameterTypes.count { it == "Ljava/lang/String;" } == 2
        } ?: false
    } >= 0

internal val getExperimentBoolFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Ljava/lang/String;", "Ljava/lang/String;", "Z")
    returns("Z")
    custom(::hasReferenceToGetAssignedProperty)
}

internal val getExperimentEnumFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Ljava/lang/String;", "Ljava/lang/String;", "Ljava/lang/Enum;")
    returns("Ljava/lang/Enum;")
    custom(::hasReferenceToGetAssignedProperty)
}

internal val getExperimentIntFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Ljava/lang/String;", "I", "I", "I", "Ljava/lang/String;")
    returns("I")
    custom(::hasReferenceToGetAssignedProperty)
}

internal val accountAttributeFingerprint = fingerprint {
    custom { _, classDef ->
        classDef.type == "Lcom/spotify/remoteconfig/internal/AccountAttribute;"
    }
}

internal val productStateProtoGetMapFingerprint = fingerprint {
    returns("Ljava/util/Map;")
    custom { _, classDef ->
        classDef.type == "Lcom/spotify/remoteconfig/internal/ProductStateProto;"
    }
}

internal val buildQueryParametersFingerprint = fingerprint {
    strings("trackRows", "device_type:tablet")
}

internal val getPackageInfoFingerprint = fingerprint {
    strings(
        "Failed to get the application signatures"
    )
}
