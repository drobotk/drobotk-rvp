package drobotk.revanced.patches.spotify

import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.iface.ClassDef
import com.android.tools.smali.dexlib2.iface.Method
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import drobotk.revanced.util.getReference

internal fun hasReferenceToGetAssignedProperty(method: Method, classDef: ClassDef): Boolean {
    method.implementation ?: return false
    return method.instructions.any { instruction ->
        instruction.getReference<MethodReference>()?.let { reference ->
            reference.definingClass == classDef.type
                    && reference.parameterTypes.count { it == "Ljava/lang/String;" } == 2
        } ?: false
    }
}

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
