package user.com.sg.socialfeeddemo.events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import user.com.sg.socialfeeddemo.events.contracts.base.Contract;

public class EventReceiver extends BroadcastReceiver {

    public static final String TAG = "EventReceiver";
    private EventListener mListener;
    private Context mContext;
    public static final String DATA = "data";

    public EventReceiver(Context context, EventListener listener) {
        mListener = listener;
        mContext  = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive ");
        if (mListener != null) {
            Object data;
            Bundle bundle = null;
            Contract contract = null;

            data = intent.getExtras().get(DATA);
            try {
                bundle = (Bundle) data;
            } catch (ClassCastException e) {
                bundle = null;
                contract = (Contract) data;
            }
            if (bundle != null) {
                ((OnEventReceivedListener)mListener).onEventReceived(intent.getAction(), bundle);
            } else if (contract != null) {
                ((OnGlobalEventReceivedListener)mListener).onEventReceived(intent.getAction(), contract);
            }
        }
    }

    public Context getContext() {
        return mContext;
    }
}
