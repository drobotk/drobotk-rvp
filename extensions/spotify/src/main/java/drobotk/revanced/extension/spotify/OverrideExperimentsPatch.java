package drobotk.revanced.extension.spotify;

import android.util.Log;

@SuppressWarnings("unused")
public class OverrideExperimentsPatch {
    private static final boolean LOG = true;
    private static final String LOG_TAG = "drobotk: OverrideExperimentsPatch";

    @SuppressWarnings("unused")
    private static <T extends Enum<T>> T getEnumFromString(T defaultValue, String value) {
        try {
            return Enum.valueOf(defaultValue.getDeclaringClass(), value);
        } catch (IllegalArgumentException ex) {
            return defaultValue;
        }
    }

    @SuppressWarnings("unused")
    public static boolean getExperimentBool(String scope, String name, boolean defaultValue, boolean value) {
        if (LOG)
            Log.d(LOG_TAG, scope + ":" + name + ":b:" + defaultValue + ":" + value);

        if (name.contains("enable")) {
            if (scope.contains("integrity"))
                return false;
        }

        if (name.equals("media3_enabled"))
            return true;

        return value;
    }

    @SuppressWarnings("unused")
    public static <T extends Enum<T>> T getExperimentEnum(String scope, String name, T defaultValue, T value) {
        if (LOG) {
            StringBuilder sb = new StringBuilder();
            T[] constants = defaultValue.getDeclaringClass().getEnumConstants();
            if (constants != null) {
                for (T constant : constants) {
                    sb.append(constant);
                    sb.append(":");
                }
                Log.d(LOG_TAG, scope + ":" + name + ":e:" + sb + defaultValue + ":" + value);
            }
        }

        return value;
    }

    @SuppressWarnings("unused")
    public static int getExperimentInt(String scope, int min, int max, int defaultValue, String name, int value) {
        if (LOG)
            Log.d(LOG_TAG, scope + ":" + name + ":i:" + min + ":" + max + ":" + defaultValue + ":" + value);

        return value;
    }
}
