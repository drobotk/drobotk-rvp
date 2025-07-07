package drobotk.revanced.patches.spotify

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.returnEarly

@Suppress("unused")
internal val disableAddingCookiesPatch = bytecodePatch(
    name = "Disable adding cookies",
) {
    compatibleWith("com.spotify.music")

    execute {
        setCookieFingerprint.method.addInstructions(
            0,
            """
                invoke-interface {p1}, Lio/reactivex/rxjava3/core/CompletableEmitter;->onComplete()V
                return-void
            """
        )
    }
}

@Suppress("unused")
internal val fixPlanViewCrashPatch = bytecodePatch(
    name = "Fix plan view crash",
    use = false
) {
    compatibleWith("com.spotify.music")

    execute {
        getPlanColorFingerprint.match(planDynamicMethodFingerprint.classDef).method.returnEarly("#000000")
    }
}

@Suppress("unused")
internal val removeYourPlanSideDrawerItemPatch = bytecodePatch(
    name = "Remove 'Your plan' side drawer item",
    use = false
) {
    compatibleWith("com.spotify.music")

    execute {
        createYourPlanSideDrawerListItemFingerprint.method.returnEarly()
    }
}
