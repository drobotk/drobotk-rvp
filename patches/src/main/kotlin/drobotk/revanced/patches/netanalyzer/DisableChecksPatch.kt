package drobotk.revanced.patches.netanalyzer

import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.extensions.InstructionExtensions.replaceInstruction
import app.revanced.patcher.patch.bytecodePatch
import drobotk.revanced.util.returnEarly
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction10t
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction21t
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import drobotk.revanced.util.indexOfFirstInstruction

private const val EXTENSION_CLASS_DESCRIPTOR = "Ldrobotk/revanced/extension/netanalyzer/DisableChecksPatch;"

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Disable checks",
) {
    extendWith("extensions/drobotk/netanalyzer.rve")

    compatibleWith("net.techet.netanalyzer.an")

    execute {
        deobfStringFingerprint.method.apply {
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
                """
                    invoke-static {v$returnRegister}, $EXTENSION_CLASS_DESCRIPTOR->debugLogOutput(Ljava/lang/String;)V
                """
            )
            addInstruction("""
                return-object v$returnRegister
            """)
        }

        whateverFingerprint.method.returnEarly()
        with(whatever2Fingerprint) {
            val strIdx = stringMatches!!.first().index
            val ifNezIdx = method.indexOfFirstInstruction(strIdx, Opcode.IF_NEZ)
            val ifNezTarget = method.getInstruction<BuilderInstruction21t>(ifNezIdx).target
            method.replaceInstruction(ifNezIdx, BuilderInstruction10t(Opcode.GOTO, ifNezTarget))
        }

        with(whatever3Fingerprint) {
            val strIdx = stringMatches!!.first().index
            method.replaceInstruction(strIdx, "return-void")
        }

//        val method = theOneWeNeedToCopyFingerprint.method

//        //val copy = method.copy()
//        //copy.removeInstructions(copy.instructions.count())
//        //copy.returnEarly()
//
//        method.apply {
//            accessFlags = accessFlags.and(AccessFlags.FINAL.value.inv())
//        }
//
//       // proxy(cannotVerifyAppLicenseFingerprint.classDef).mutableClass.methods.add(copy)
    }
}