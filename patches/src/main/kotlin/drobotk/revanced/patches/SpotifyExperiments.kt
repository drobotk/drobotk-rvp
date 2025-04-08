package drobotk.revanced.patches

import app.revanced.patcher.fingerprint
import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.replaceInstructions
import app.revanced.patcher.util.proxy.mutableTypes.MutableMethod.Companion.toMutable
import com.android.tools.smali.dexlib2.iface.reference.Reference
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import com.android.tools.smali.dexlib2.iface.instruction.Instruction
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.builder.MutableMethodImplementation
import com.android.tools.smali.dexlib2.immutable.ImmutableMethod
import com.android.tools.smali.dexlib2.immutable.ImmutableMethodParameter
import app.revanced.patcher.util.proxy.mutableTypes.MutableClass
import com.android.tools.smali.dexlib2.iface.Method
import app.revanced.patcher.patch.bytecodePatch

inline fun <reified T : Reference> Instruction.getReference() = (this as? ReferenceInstruction)?.reference as? T

internal val getExperimentBoolFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Ljava/lang/String;", "Ljava/lang/String;", "Z")
    returns("Z")
    // opcodes(
    //     Opcode.CONST_4,
    //     Opcode.INVOKE_VIRTUAL,
    //     Opcode.MOVE_RESULT_OBJECT,
    //     Opcode.IF_EQZ,
    //     Opcode.IGET_OBJECT,
    //     Opcode.IF_EQZ,
    //     Opcode.INVOKE_VIRTUAL,
    //     Opcode.MOVE_RESULT,
    //     Opcode.RETURN
    // )
}

internal val getExperimentEnumFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Ljava/lang/String;", "Ljava/lang/String;", "Ljava/lang/Enum;")
    returns("Ljava/lang/Enum;")
    // opcodes(
    //     Opcode.CONST_4,
    //     Opcode.INVOKE_VIRTUAL,
    //     Opcode.MOVE_RESULT_OBJECT,
    //     Opcode.CONST_4,
    //     Opcode.IF_EQZ,
    //     Opcode.IGET_OBJECT,
    //     Opcode.GOTO,
    //     Opcode.MOVE_OBJECT,
    // )
}

internal val getExperimentIntFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC)
    parameters("Ljava/lang/String;", "Ljava/lang/String;", "I", "I", "I")
    returns("I")
}

internal fun MutableClass.copyMethod(method: Method, copyName: String) =
    methods.add(
        ImmutableMethod(
            type,
            copyName,
            method.parameters,
            method.returnType,
            method.accessFlags,
            method.annotations,
            method.hiddenApiRestrictions,
            method.implementation
    ).toMutable())

@Suppress("unused")
val experimentsPatch = bytecodePatch(
    name = "Experiments"
) {
    compatibleWith("com.spotify.music")

    extendWith("extensions/drobotk/spotify.rve")

    execute {
        with(getExperimentBoolFingerprint) {
            println(method)

            val trampolineName = "trampoline_getExperimentBool"

            classDef.copyMethod(method, trampolineName)

            method.addInstructions(
                0,
                """
                    invoke-virtual {p0, p1, p2, p3}, $classDef->$trampolineName(Ljava/lang/String;Ljava/lang/String;Z)Z

                    move-result v0

                    invoke-static {p1, p2, p3, v0}, Ldrobotk/revanced/spotify/Experiments;->getExperimentBool(Ljava/lang/String;Ljava/lang/String;ZZ)Z

                    move-result v0

                    return v0
                """
            )
        }

        with(getExperimentEnumFingerprint) {
            println(method)

            val trampolineName = "trampoline_getExperimentEnum"

            classDef.copyMethod(method, trampolineName)

            method.addInstructions(
                0,
                """
                    invoke-virtual {p0, p1, p2, p3}, $classDef->$trampolineName(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Enum;)Ljava/lang/Enum;

                    move-result-object v0

                    invoke-static {p1, p2, p3, v0}, Ldrobotk/revanced/spotify/Experiments;->getExperimentEnum(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Enum;Ljava/lang/Enum;)Ljava/lang/Enum;

                    move-result-object v0

                    return-object v0
                """
            )
        }

        with(getExperimentIntFingerprint) {
            println(method)
            val trampolineName = "trampoline_getExperimentInt"

            classDef.copyMethod(method, trampolineName)

            method.addInstructions(
                0,
                """
                    invoke-virtual/range {p0 .. p5}, $classDef->$trampolineName(Ljava/lang/String;Ljava/lang/String;III)I

                    move-result v0

                    move-object p0, p1
                    move-object p1, p2
                    move p2, p3
                    move p3, p4
                    move p4, p5
                    move p5, v0

                    invoke-static/range {p0 .. p5}, Ldrobotk/revanced/spotify/Experiments;->getExperimentInt(Ljava/lang/String;Ljava/lang/String;IIII)I

                    move-result v0

                    return v0
                """
            )
        }
    }
}