package user.com.sg.socialfeeddemo.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by ianicasiano on 2/26/16.
 *
 * Utility class for service related tasks
 */
public class ServiceUtils {

    public static void requestForService(java.lang.Class<?> cls, Activity activity) {
        requestForService(null, null, cls, activity);
    }

    public static void requestForService(String identifier, String intent, java.lang.Class<?> cls, Activity activity) {
        Intent serviceIntent = new Intent(activity, cls);

        if (identifier != null && intent != null) {
            serviceIntent.putExtra(identifier, intent);
        }

        activity.startService(serviceIntent);
    }
}
