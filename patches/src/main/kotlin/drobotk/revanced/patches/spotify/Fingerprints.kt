package drobotk.revanced.patches.spotify

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.iface.ClassDef
import com.android.tools.smali.dexlib2.iface.Method
import com.android.tools.smali.dexlib2.iface.reference.FieldReference
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import app.revanced.util.getReference
import app.revanced.util.indexOfFirstInstruction

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

internal val setCookieFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Lio/reactivex/rxjava3/core/CompletableEmitter;")
    returns("V")
    custom { method, _ ->
        method.name == "subscribe" && method.indexOfFirstInstruction {
            getReference<MethodReference>()?.name == "setCookie"
        } >= 0
    }
}

internal val planDynamicMethodFingerprint = fingerprint {
    strings("bitField0_", "planType_", "planTier_", "planName_", "shortPlanName_", "planColor_")
}

internal val getPlanColorFingerprint = fingerprint {
    returns("Ljava/lang/String;")
    custom { method, _ ->
        method.indexOfFirstInstruction {
            getReference<FieldReference>()?.name == "planColor_"
        } >= 0
    }
}

internal val createYourPlanSideDrawerListItemFingerprint = fingerprint {
    parameters("L", "Ljava/lang/String;", "Ljava/lang/String;", "Z", "Z", "Z", "I")
    returns("L")
    strings("#FFFFFF")
}
