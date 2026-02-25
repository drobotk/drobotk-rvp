package drobotk.revanced.patches.spotify

import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.iface.Method
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import app.revanced.util.getReference
import app.revanced.util.indexOfFirstInstruction

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

internal val BytecodePatchContext.accountAttributeClassDef by gettingFirstClassDef {
    type == "Lcom/spotify/remoteconfig/internal/AccountAttribute;"
}

internal val BytecodePatchContext.productStateProtoGetMapMethod by gettingFirstMethodDeclaratively {
    returnType("Ljava/util/Map;")
    definingClass("Lcom/spotify/remoteconfig/internal/ProductStateProto;")
}

internal val BytecodePatchContext.buildQueryParametersMethodMatch by composingFirstMethod("device_type:tablet") {
    instructions("trackRows"())
}

internal val BytecodePatchContext.getPackageInfoMethodMatch by composingFirstMethod {
    instructions("Failed to get the application signatures"())
}
