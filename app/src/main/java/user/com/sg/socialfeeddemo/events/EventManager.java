package user.com.sg.socialfeeddemo.events;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import user.com.sg.socialfeeddemo.events.contracts.base.Contract;

public class EventManager {

    public static final String TAG = "EventManager";
    private static EventManager ourInstance;
    private HashMap<EventListener, String[]> listeners;
    private HashMap<String, Parcelable> queue;

    private EventFactory mCommandCenter;

    private EventManager() {
        listeners = new HashMap<>();
        queue = new HashMap<>();

    }
    public static EventManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new EventManager();
        }
        return ourInstance;
    }

    public void initializeCommandCenter(Context context) {
        mCommandCenter = EventFactory.newInstance(context);
        addEventListener(mCommandCenter, EventConstants.EVENT_TASK_EXECUTE);
    }

    public void destroy() {
        removeEventListener(mCommandCenter);
    }

    public void addEventListener(@NonNull EventListener listener,@NonNull String... events) {
        EventReceiver receiver = listener.getEventReceiver();
        if (receiver == null) {
            receiver = listener.initializeEventReceiver();
        }
        if (receiver == null) {
            throw new RuntimeException("Custom event receiver cannot be null");
        }

        Log.i(TAG, "addEventListener ");
        Context context = receiver.getContext();
        IntentFilter intentFilter = new IntentFilter();
        for(String event : events) {
            intentFilter.addAction(event);
            Log.i(TAG, "Registering " + event);
        }
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, intentFilter);
        listeners.put(listener, events);
        notifyQueue(context, events);
    }

    private void notifyQueue(Context context, String[] events) {
        for (String event : events) {
            if (queue.containsKey(event)) {
                broadcast(context, event, queue.get(event));
                queue.remove(event);
            }
        }
    }

    public void removeEventListener(@NonNull EventListener listener) {
        EventReceiver receiver = listener.getEventReceiver();
        if (receiver == null) {
            receiver = listener.initializeEventReceiver();
        }

        if (receiver == null) {
            throw new RuntimeException("Custom event receiver cannot be null");
        }
        Context context = receiver.getContext();
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
        listeners.remove(listener);
        Log.d(TAG, "Receiver Unregistered");
    }

    public void broadcastContract(Context context, Contract contract) {
        this.broadcast(context, EventConstants.EVENT_TASK_EXECUTE, contract);
    }

    public void broadcast(Context context, String event, Parcelable extras) {
        Log.i(TAG, "broadcasting " + event);
        Intent i = new Intent(event);
        if (extras != null) {
            i.putExtra(EventReceiver.DATA, extras);
        }

        // before sending, check first if there's an existing listener for this event.
        Collection<String[]> vals = listeners.values();
        Iterator<String[]> iterator = vals.iterator();
        while (iterator.hasNext()) {
            String[] contents = iterator.next();
            final Set<String> VALUES = new HashSet<>(Arrays.asList(
                    contents
            ));

            if (VALUES.contains(event)) {
                // event is found. Continue with broadcasting
                LocalBroadcastManager.getInstance(context).sendBroadcast(i);
                return;
            }
        }
        // event is not found, add to queue.
        queue.put(event, extras);
    }

    public void broadcast(Context context, String event) {
        broadcast(context, event, new Bundle());
    }

}
