package drobotk.revanced.patches.spotify

import app.revanced.patcher.MutablePredicateList
import app.revanced.patcher.accessFlags
import app.revanced.patcher.composingFirstMethod
import app.revanced.patcher.definingClass
import app.revanced.patcher.gettingFirstClassDef
import app.revanced.patcher.gettingFirstMethodDeclaratively
import app.revanced.patcher.instructions
import app.revanced.patcher.invoke
import app.revanced.patcher.opcodes
import app.revanced.patcher.parameterTypes
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.predicate
import app.revanced.patcher.returnType
import app.revanced.util.getReference
import app.revanced.util.indexOfFirstInstruction
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.Method
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

internal fun MutablePredicateList<Method>.hasReferenceToGetAssignedProperty() = predicate {
    indexOfFirstInstruction {
        getReference<MethodReference>()?.let { reference ->
            reference.definingClass == definingClass
                    && reference.parameterTypes.count { it == "Ljava/lang/String;" } == 2
        } ?: false
    } >= 0
}

internal val BytecodePatchContext.getExperimentBoolMethod by gettingFirstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    parameterTypes("Ljava/lang/String;", "Ljava/lang/String;", "Z")
    returnType("Z")
    hasReferenceToGetAssignedProperty()
}

internal val BytecodePatchContext.getExperimentEnumMethod by gettingFirstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    parameterTypes("Ljava/lang/String;", "Ljava/lang/String;", "Ljava/lang/Enum;")
    returnType("Ljava/lang/Enum;")
    hasReferenceToGetAssignedProperty()
}

internal val BytecodePatchContext.getExperimentIntMethod by gettingFirstMethodDeclaratively {
    accessFlags(AccessFlags.PUBLIC)
    parameterTypes("Ljava/lang/String;", "I", "I", "I", "Ljava/lang/String;")
    returnType("I")
    hasReferenceToGetAssignedProperty()
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
