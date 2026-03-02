package drobotk.revanced.patches.spotify

import app.revanced.com.android.tools.smali.dexlib2.mutable.MutableMethod
import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val BytecodePatchContext.getAssignedPropertyMethod by gettingFirstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    returnType("Lp/")
    parameterTypes("I", "Ljava/lang/String", "Ljava/lang/String")
    instructions(
        predicates = unorderedAllOf(
            reference("Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object"),
            1.toLong()(),
            2.toLong()(),
            3.toLong()(),
            4.toLong()()
        )
    )
}

internal fun BytecodePatchContext.getGetExperimentBoolMethod(getAssignedPropertyMethod: MutableMethod) = firstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    parameterTypes("Ljava/lang/String;", "Ljava/lang/String;", "Z")
    returnType("Z")
    instructions(
        method {
            equals(getAssignedPropertyMethod)
        }
    )
}

internal fun BytecodePatchContext.getGetExperimentEnumMethod(getAssignedPropertyMethod: MutableMethod) = firstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    parameterTypes("Ljava/lang/String;", "Ljava/lang/String;", "Ljava/lang/Enum;")
    returnType("Ljava/lang/Enum;")
    instructions(
        method {
            equals(getAssignedPropertyMethod)
        }
    )
}

internal fun BytecodePatchContext.getGetExperimentIntMethod(getAssignedPropertyMethod: MutableMethod) = firstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    parameterTypes("Ljava/lang/String;", "I", "I", "I", "Ljava/lang/String;")
    returnType("I")
    instructions(
        method {
            equals(getAssignedPropertyMethod)
        }
    )
}

internal val BytecodePatchContext.accountAttributeClassDef by gettingFirstClassDef("Lcom/spotify/remoteconfig/internal/AccountAttribute;")

internal val BytecodePatchContext.productStateProtoGetMapMethodMatch by composingFirstMethod {
    returnType("Ljava/util/Map;")
    definingClass("Lcom/spotify/remoteconfig/internal/ProductStateProto;")
    opcodes(Opcode.IGET_OBJECT)
}

internal val BytecodePatchContext.buildQueryParametersMethodMatch by composingFirstMethod("device_type:tablet") {
    instructions("trackRows"())
}

internal val BytecodePatchContext.getPackageInfoMethodMatch by composingFirstMethod {
    instructions("Failed to get the application signatures"())
}
