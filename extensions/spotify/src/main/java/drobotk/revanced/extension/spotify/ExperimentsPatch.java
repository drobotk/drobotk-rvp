package drobotk.revanced.extension.spotify;

import android.util.Log;

@SuppressWarnings("unused")
public class ExperimentsPatch {
    private static final boolean LOG = false;
    private static final String LOG_TAG = "drobotk: Experiments";

    private static Enum getEnumFromString(Enum parentEnum, String value) {
        Enum[] values = (Enum[]) parentEnum.getDeclaringClass().getEnumConstants();
        for (Enum val : values)
            if (val.toString().equals(value))
                return val;

        return parentEnum;
    }

    public static boolean getExperimentBool(String scope, String name, boolean defaultValue, boolean value) {
        if (LOG)
            Log.d(LOG_TAG, scope + ":" + name + ":b:" + defaultValue + ":" + value);

        if (name.contains("enabled")) {
            if (scope.contains("integrity"))
                return false;

            if (scope.contains("upsell") || name.contains("upsell"))
                return false;
        }

        if (name.equals("enable_available_plans_v2")
            || name.equals("enable_your_plan_sidedrawer")
            || name.equals("enable_rate_limiter"))
            return false;

//        if (name.equals("validation_enabled") || name.equals("time_tracking_enabled"))
//            return true;

        return value;
    }

    public static Enum getExperimentEnum(String scope, String name, Enum defaultValue, Enum value) {
        if (LOG) {
            StringBuilder sb = new StringBuilder();
            Enum[] values = (Enum[]) defaultValue.getDeclaringClass().getEnumConstants();
            for (Enum val : values) {
                sb.append(val.toString());
                sb.append(":");
            }
            Log.d(LOG_TAG, scope + ":" + name + ":e:" + sb.toString() + defaultValue + ":" + value);
        }

        return value;
    }

    public static int getExperimentInt(String scope, int min, int max, int defaultValue, String name, int value) {
        if (LOG)
            Log.d(LOG_TAG, scope + ":" + name + ":i:" + min + ":" + max + ":" + defaultValue + ":" + value);

        return value;
    }

//    public static void logObject(Object object) {
//        Log.d("drobotk logObject", "" + object);
//    }
}
