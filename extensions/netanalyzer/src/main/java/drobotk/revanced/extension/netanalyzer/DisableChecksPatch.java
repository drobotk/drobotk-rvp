package drobotk.revanced.extension.netanalyzer;

import android.util.Log;

@SuppressWarnings("unused")
public class DisableChecksPatch {
    private static final String LOG_TAG = "DisableChecksPatch";

    @SuppressWarnings("unused")
    public static void debugLogInput(String str) {
        Log.d(LOG_TAG, "in: " + str);
    }

    @SuppressWarnings("unused")
    public static void debugLogOutput(String str) {
        Log.d(LOG_TAG, "out: " + str);

        if (str.contains("Cannot verify app license")) {
            new Exception().printStackTrace();
        }
    }
}
