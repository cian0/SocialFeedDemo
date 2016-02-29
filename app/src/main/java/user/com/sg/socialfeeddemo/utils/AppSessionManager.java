package user.com.sg.socialfeeddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import user.com.sg.socialfeeddemo.database.model.Profile;
import user.com.sg.socialfeeddemo.events.EventConstants;
import user.com.sg.socialfeeddemo.events.EventManager;
import user.com.sg.socialfeeddemo.events.EventReceiver;
import user.com.sg.socialfeeddemo.events.OnEventReceivedListener;
import user.com.sg.socialfeeddemo.services.DataFetcherService;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class AppSessionManager extends Session {


    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "id";
    public static final String KEY_PHOTO_PATH = "photo_path";

    private static AppSessionManager instance;
    private EventReceiver sessionReceiver;
    OnEventReceivedListener sessionListener;

    public static AppSessionManager getInstance(Context context) {
        if(instance == null) {
            instance = getSync(context);
        }
        return instance;
    }
    private static synchronized AppSessionManager getSync(Context context) {
        if(instance == null)
            instance = new AppSessionManager(context);
        return instance;
    }

    private AppSessionManager(Context context) {
        super(context);
    }

    /**
     * For this application, just clear the whole session object on first time and reinsert all data to database.
     */
    public void setupFirsttime(final Activity activity, final Runnable onFinish) {
        mEditor.clear();
        mEditor.commit();
        sessionListener = new OnEventReceivedListener() {
            @Override
            public void onEventReceived(String event, Bundle data) {
                switch (event) {
                    case EventConstants.PARSE_FINISHED:
                        onFinish.run();
                        EventManager.getInstance().removeEventListener(sessionListener);
                        break;
                    case EventConstants.PARSE_FAILED:
                        Toast.makeText(activity, "Parsing data failed. Please fix data.", Toast.LENGTH_LONG);
                        EventManager.getInstance().removeEventListener(sessionListener);
                        break;
                }
            }

            @Override
            public EventReceiver getEventReceiver() {
                return sessionReceiver;
            }

            @Override
            public EventReceiver initializeEventReceiver() {
                return sessionReceiver;
            }
        };
        sessionReceiver = new EventReceiver(activity, sessionListener);

        EventManager.getInstance().addEventListener(sessionListener, EventConstants.PARSE_FINISHED, EventConstants.PARSE_FAILED);

        ServiceUtils.requestForService(DataFetcherService.class, activity);

    }

    /**
     * Creates new user session
     * @param profile user's profile.
     */
    public void createUserSession(Profile profile) {
        mEditor.putString(KEY_NAME, profile.getName());
        mEditor.putString(KEY_PHOTO_PATH, profile.getPhotoPath());
        mEditor.putInt(KEY_ID, profile.getId());
        mEditor.commit();
    }
}
