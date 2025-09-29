package drobotk.revanced.patches.spotify

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.extensions.InstructionExtensions.removeInstructions
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patcher.util.proxy.mutableTypes.MutableMethod
import app.revanced.patcher.util.proxy.mutableTypes.MutableMethod.Companion.toMutable
import com.android.tools.smali.dexlib2.immutable.ImmutableMethod

private const val EXTENSION_CLASS_DESCRIPTOR = "Ldrobotk/revanced/extension/spotify/OverrideExperimentsPatch;"

private fun MutableMethod.copy() =
    ImmutableMethod(
        definingClass,
        name,
        parameters,
        returnType,
        accessFlags,
        annotations,
        hiddenApiRestrictions,
        implementation
    ).toMutable()

@Suppress("unused")
val overrideExperimentsPatch = bytecodePatch(
    name = "Override experiments"
) {
    compatibleWith("com.spotify.music")

    extendWith("extensions/drobotk/spotify.rve")

    execute {
        with(getExperimentBoolFingerprint) {
            println(method)

            val trampolineMethod = method.copy().apply {
                name = "trampoline_getExperimentBool"
                classDef.methods.add(this)
            }

            method.removeInstructions(method.instructions.count())
            method.addInstructions(
                """
                    invoke-virtual {p0, p1, p2, p3}, $trampolineMethod

                    move-result v0

                    invoke-static {p1, p2, p3, v0}, $EXTENSION_CLASS_DESCRIPTOR->getExperimentBool(Ljava/lang/String;Ljava/lang/String;ZZ)Z

                    move-result v0

                    return v0
                """
            )
        }

        with(getExperimentEnumFingerprint) {
            println(method)

            val trampolineMethod = method.copy().apply {
                name = "trampoline_getExperimentEnum"
                classDef.methods.add(this)
            }

            method.removeInstructions(method.instructions.count())
            method.addInstructions(
                """
                    invoke-virtual {p0, p1, p2, p3}, $trampolineMethod

                    move-result-object v0

                    invoke-static {p1, p2, p3, v0}, $EXTENSION_CLASS_DESCRIPTOR->getExperimentEnum(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Enum;Ljava/lang/Enum;)Ljava/lang/Enum;

                    move-result-object v0

                    return-object v0
                """
            )
        }

        with(getExperimentIntFingerprint) {
            println(method)

            val trampolineMethod = method.copy().apply {
                name = "trampoline_getExperimentInt"
                classDef.methods.add(this)
            }

            method.removeInstructions(method.instructions.count())
            method.addInstructions(
                """
                    invoke-virtual/range {p0 .. p5}, $trampolineMethod

                    move-result v0

                    move-object p0, p1
                    move p1, p2
                    move p2, p3
                    move p3, p4
                    move-object p4, p5
                    move p5, v0

                    invoke-static/range {p0 .. p5}, $EXTENSION_CLASS_DESCRIPTOR->getExperimentInt(Ljava/lang/String;IIILjava/lang/String;I)I

                    move-result v0

                    return v0
                """
            )
        }
    }
}
