package user.com.sg.socialfeeddemo.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static String rawResourceToString(Context context, int resId) {
        String text;
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(resId);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            text = (new String(b));
        } catch (Exception e) {
            // e.printStackTrace();
            text = null;
        }
        return text;
    }

    /**
     * Converts raw resource to JSON object.
     * @param context Our context to obtain raw resource from
     * @param resId resource ID of raw resource
     * @return the JSON object created, null otherwise
     */
    public static JSONObject rawResourceToJson(Context context, int resId) {
        String text;
        JSONObject json = null;
        text = rawResourceToString(context, resId);
        if (text != null) {
            try {
                json = new JSONObject(text);
            } catch (JSONException e) {
                e.printStackTrace();
                json = null;
            }
        }
        return json;
    }

    public static JSONArray rawResourceToJsonArray(Context context, int resId) {
        String text;
        JSONArray json = null;
        text = rawResourceToString(context, resId);
        if (text != null) {
            try {
                json = new JSONArray(text);
            } catch (JSONException e) {
                e.printStackTrace();
                json = null;
            }
        }
        return json;
    }
}
