package drobotk.revanced.patches.spotify

import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.removeInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.indexOfFirstInstructionOrThrow
import app.revanced.util.indexOfFirstInstructionReversedOrThrow
import app.revanced.util.toPublicAccessFlags
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

private const val EXTENSION_CLASS_DESCRIPTOR = "Ldrobotk/revanced/extension/spotify/OverrideAccountAttributesPatch;"

@Suppress("unused")
val overrideAccountAttributesPatch = bytecodePatch(
    name = "Override account attributes"
) {
    compatibleWith("com.spotify.music")

    extendWith("extensions/drobotk/spotify.rve")

    execute {
        // Make value_ public so that it can be overridden in the extension.
        accountAttributeFingerprint.classDef.fields.first {
            it.name == "value_"
        }.apply {
            accessFlags = accessFlags.toPublicAccessFlags()
        }

        productStateProtoGetMapFingerprint.method.apply {
            val getAttributesMapIndex = indexOfFirstInstructionOrThrow(Opcode.IGET_OBJECT)
            val attributesMapRegister = getInstruction<TwoRegisterInstruction>(getAttributesMapIndex).registerA

            addInstruction(
                getAttributesMapIndex + 1,
                """
                    invoke-static {v$attributesMapRegister}, $EXTENSION_CLASS_DESCRIPTOR->overrideAttributes(Ljava/util/Map;)V
                """
            )
        }

        // Add the query parameter trackRows to show popular tracks in the artist page.
        with(buildQueryParametersFingerprint) {
            val addQueryParameterConditionIndex = method.indexOfFirstInstructionReversedOrThrow(
                stringMatches!!.first().index, Opcode.IF_EQZ
            )

            method.removeInstruction(addQueryParameterConditionIndex)
        }
    }
}
