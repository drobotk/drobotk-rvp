package drobotk.revanced.spotify;

import android.util.Log;

public class Experiments {
    public static boolean getExperimentBool(String scope, String name, boolean defaultValue, boolean value) {
        //Log.d("Experiments", scope + ":" + name + ":" + defaultValue + ":" + value);

        if (scope.equals("android-list-ux-platform-consumers-likedsongs-shared")) {
            return true;
        }

        return value;
    }
    public static Enum getExperimentEnum(String scope, String name, Enum defaultValue, Enum value) {
        //Log.d("Experiments", scope + ":" + name + ":" + defaultValue + ":" + value);

        if (name.equals("create_button_position")) {
            Enum[] values = (Enum[]) defaultValue.getDeclaringClass().getEnumConstants();
            for (Enum val : values) {
                if (val.toString().equals("RIGHT")) {
                    return val;
                }
            }
        }
        return value;
    }
    public static int getExperimentInt(String scope, String name, int min, int max, int defaultValue, int value) {
        //Log.d("Experiments", scope + ":" + name + ":" + min + ":" + max + ":" + defaultValue + ":" + value);
        return value;
    }
}

