package user.com.sg.socialfeeddemo.events;

import android.content.Context;

import user.com.sg.socialfeeddemo.events.contracts.base.Contract;
import user.com.sg.socialfeeddemo.events.tasks.base.EventTask;

public class EventFactory implements OnGlobalEventReceivedListener {

    private final String TAG = "EventFactory";
    private final EventReceiver mEventReceiver;
    private final Context mContext;

    public static EventFactory newInstance(Context context) {
        return new EventFactory(context);
    }

    private EventFactory(Context context) {
        mContext = context;
        mEventReceiver = new EventReceiver(context, this);
    }

    @Override
    public void onEventReceived(String event, Contract contract) {
        //command
        EventTask task;
        if(contract == null) {
            return;
        }

        task = contract.newTask(mContext);

        if(task!=null)
            task.execute();
    }

    @Override
    public EventReceiver getEventReceiver() {
        return mEventReceiver;
    }

    @Override
    public EventReceiver initializeEventReceiver() {
        return mEventReceiver;
    }
}
