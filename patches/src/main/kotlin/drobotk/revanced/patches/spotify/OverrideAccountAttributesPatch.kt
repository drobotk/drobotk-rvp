package drobotk.revanced.patches.spotify

import app.revanced.patcher.extensions.addInstruction
import app.revanced.patcher.extensions.getInstruction
import app.revanced.patcher.extensions.removeInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.indexOfFirstInstructionReversedOrThrow
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

private const val EXTENSION_CLASS_DESCRIPTOR = "Ldrobotk/revanced/extension/spotify/OverrideAccountAttributesPatch;"

@Suppress("unused")
val overrideAccountAttributesPatch = bytecodePatch(
    name = "Override account attributes"
) {
    compatibleWith("com.spotify.music")

    extendWith("extensions/drobotk/spotify.rve")

    apply {
        // Make value_ public so that it can be overridden in the extension.
        accountAttributeClassDef.fields.first {
            it.name == "value_"
        }.apply {
            accessFlags = accessFlags.or(AccessFlags.PUBLIC.value)
                .and(AccessFlags.PROTECTED.value.inv())
                .and(AccessFlags.PRIVATE.value.inv())
        }

        productStateProtoGetMapMethodMatch.method.apply {
            val getAttributesMapIndex = productStateProtoGetMapMethodMatch[0]
            val attributesMapRegister = getInstruction<TwoRegisterInstruction>(getAttributesMapIndex).registerA

            addInstruction(
                getAttributesMapIndex + 1,
                """
                    invoke-static {v$attributesMapRegister}, $EXTENSION_CLASS_DESCRIPTOR->overrideAttributes(Ljava/util/Map;)V
                """
            )
        }

        // Add the query parameter trackRows to show popular tracks in the artist page.
        buildQueryParametersMethodMatch.method.apply {
            val addQueryParameterConditionIndex = indexOfFirstInstructionReversedOrThrow(
                buildQueryParametersMethodMatch[0], Opcode.IF_EQZ
            )

            removeInstruction(addQueryParameterConditionIndex)
        }
    }
}
