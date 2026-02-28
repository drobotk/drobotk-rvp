package drobotk.revanced.extension.spotify;

import android.util.Log;

import com.spotify.remoteconfig.internal.AccountAttribute;

import java.util.Map;

@SuppressWarnings("unused")
public class OverrideAccountAttributesPatch {
    private static final boolean LOG_ALL = true;
    private static final String LOG_TAG = "drobotk-OverrideAccountAttributesPatch";

    private static final Map<String, Object> OVERRIDES = Map.ofEntries(
            Map.entry("ads", false),
            Map.entry("player-license", "premium"),
            Map.entry("player-license-v2", "premium"),
            Map.entry("shuffle", false),
            Map.entry("on-demand", true),
            Map.entry("pick-and-shuffle", false),
            Map.entry("streaming-rules", ""),
            Map.entry("nft-disabled", "1"),
            Map.entry("type", "premium"),
            Map.entry("tablet-free", false),
            Map.entry("offline", true),
            Map.entry("audio-quality", "2"),
            Map.entry("high-bitrate", true),
            Map.entry("is-pigeon", true)
    );
    @SuppressWarnings("unused")
    public static void overrideAttributes(Map<String, AccountAttribute> attributes) {
        if (LOG_ALL) {
            for (Map.Entry<String, AccountAttribute> entry : attributes.entrySet()) {
                Object value = entry.getValue().value_;
                Log.d(LOG_TAG, entry.getKey() + ":" + value.getClass().getName() + ":" + value);
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

            if (!overrideValue.getClass().equals(originalValue.getClass())) {
                Log.d(LOG_TAG, "CLASS MISMATCH FOR OVERRIDE VALUE OF " + overrideKey);
                continue;
            }

            Log.d(LOG_TAG, overrideKey + " " + originalValue + " -> " + overrideValue);

            attribute.value_ = overrideValue;
        }
    }
}
