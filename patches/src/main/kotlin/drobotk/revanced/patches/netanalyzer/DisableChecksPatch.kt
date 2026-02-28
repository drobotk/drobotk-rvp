package drobotk.revanced.patches.netanalyzer

import app.revanced.patcher.extensions.addInstruction
import app.revanced.patcher.extensions.getInstruction
import app.revanced.patcher.extensions.instructions
import app.revanced.patcher.extensions.replaceInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.indexOfFirstInstructionOrThrow
import app.revanced.util.returnEarly
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction10t
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction21t
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

private const val EXTENSION_CLASS_DESCRIPTOR = "Ldrobotk/revanced/extension/netanalyzer/DisableChecksPatch;"

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Disable checks",
) {
    extendWith("extensions/drobotk/netanalyzer.rve")

    compatibleWith("net.techet.netanalyzer.an")

    apply {
        deobfStringMethod.apply {
            addInstruction(
                0,
                """
                    invoke-static {p0}, $EXTENSION_CLASS_DESCRIPTOR->debugLogInput(Ljava/lang/String;)V
                """
            )
            val returnIdx = instructions.indexOfLast { it.opcode == Opcode.RETURN_OBJECT }
            val returnRegister = getInstruction<OneRegisterInstruction>(returnIdx).registerA
            replaceInstruction(
                returnIdx,
                "invoke-static {v$returnRegister}, $EXTENSION_CLASS_DESCRIPTOR->debugLogOutput(Ljava/lang/String;)V"
            )
            addInstruction("return-object v$returnRegister")
        }

        whateverMethod1.returnEarly()

        whateverMethod2Match.method.apply {
            val strIdx = whateverMethod2Match[0]
            val ifNezIdx = indexOfFirstInstructionOrThrow(strIdx, Opcode.IF_NEZ)
            val ifNezTarget = getInstruction<BuilderInstruction21t>(ifNezIdx).target
            replaceInstruction(ifNezIdx, BuilderInstruction10t(Opcode.GOTO, ifNezTarget))
        }

        whateverMethod3Match.let {
            it.method.replaceInstruction(it[0], "return-void")
        }
    }
}