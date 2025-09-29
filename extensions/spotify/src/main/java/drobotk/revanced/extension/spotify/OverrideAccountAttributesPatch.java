package drobotk.revanced.extension.spotify;

import android.util.Log;
import com.spotify.remoteconfig.internal.AccountAttribute;

import java.util.Map;

@SuppressWarnings("unused")
public class OverrideAccountAttributesPatch {
    private static final boolean LOG = true;
    private static final String LOG_TAG = "drobotk: OverrideAccountAttributesPatch";

    private static final Map<String, Object> OVERRIDES = Map.of(
            "ads", false,
            "player-license", "premium",
            "player-license-v2", "premium",
            "shuffle", false,
            "on-demand", true,
            "pick-and-shuffle", false,
            "streaming-rules", "",
            "nft-disabled", "1",
            "type", "premium",
            "tablet-free", false
    );
    @SuppressWarnings("unused")
    public static void overrideAttributes(Map<String, AccountAttribute> attributes) {
        if (LOG) {
            for (Map.Entry<String, AccountAttribute> entry : attributes.entrySet()) {
                Log.d(LOG_TAG, entry.getKey() + ":" + entry.getValue().value_);
            }
        }

        for (Map.Entry<String, Object> override : OVERRIDES.entrySet()) {
            Object overrideKey = override.getKey();

            AccountAttribute attribute = attributes.get(overrideKey);
            if (attribute == null)
                continue;

            Object overrideValue = override.getValue();
            Object originalValue = attribute.value_;

            if (overrideValue.equals(originalValue))
                continue;

            Log.d(LOG_TAG, overrideKey + " " + originalValue + " -> " + overrideValue);

            attribute.value_ = overrideValue;
        }
    }
}
