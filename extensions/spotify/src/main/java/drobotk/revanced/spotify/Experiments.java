package drobotk.revanced.spotify;

import android.util.Log;

public class Experiments {
    public static boolean getExperimentBool(String scope, String name, boolean defaultValue, boolean value) {
//        Log.d("Experiments", scope + ":" + name + ":b:" + defaultValue + ":" + value);

        return value;
    }
    public static Enum getExperimentEnum(String scope, String name, Enum defaultValue, Enum value) {
//        StringBuilder sb = new StringBuilder();
//        Enum[] values = (Enum[]) defaultValue.getDeclaringClass().getEnumConstants();
//        for (Enum val : values) {
//            sb.append(val.toString());
//            sb.append(":");
//        }
//        Log.d("Experiments", scope + ":" + name + ":e:" + sb.toString() + defaultValue + ":" + value);

        return value;
    }
    public static int getExperimentInt(String scope, String name, int min, int max, int defaultValue, int value) {
//        Log.d("Experiments", scope + ":" + name + ":i:" + min + ":" + max + ":" + defaultValue + ":" + value);

        return value;
    }
}

