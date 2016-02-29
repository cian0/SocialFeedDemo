package user.com.sg.socialfeeddemo.events;

import android.os.Bundle;

public interface OnEventReceivedListener extends EventListener<Bundle>{
    void onEventReceived(String event, Bundle data);
}
