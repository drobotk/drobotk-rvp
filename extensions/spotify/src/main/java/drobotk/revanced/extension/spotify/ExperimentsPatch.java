package drobotk.revanced.extension.spotify;

import android.util.Log;

public class ExperimentsPatch {
    private static final boolean LOG = false;

    private static Enum getEnumFromString(Enum parentEnum, String value) {
        Enum[] values = (Enum[]) parentEnum.getDeclaringClass().getEnumConstants();
        for (Enum val : values)
            if (val.toString().equals(value))
                return val;

        return parentEnum;
    }

    public static boolean getExperimentBool(String scope, String name, boolean defaultValue, boolean value) {
        if (LOG)
            Log.d("Experiments", scope + ":" + name + ":b:" + defaultValue + ":" + value);

        if (scope.contains("integrity") && name.contains("enabled"))
            return false;

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
            Log.d("Experiments", scope + ":" + name + ":e:" + sb.toString() + defaultValue + ":" + value);
        }

        return value;
    }

    public static int getExperimentInt(String scope, int min, int max, int defaultValue, String name, int value) {
        if (LOG)
            Log.d("Experiments", scope + ":" + name + ":i:" + min + ":" + max + ":" + defaultValue + ":" + value);

        return value;
    }

//    public static void logObject(Object object) {
//        Log.d("drobotk logObject", "" + object);
//    }
}
