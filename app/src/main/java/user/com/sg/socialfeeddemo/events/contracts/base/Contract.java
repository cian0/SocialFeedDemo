package user.com.sg.socialfeeddemo.events.contracts.base;

import android.content.Context;
import android.os.Parcelable;

import user.com.sg.socialfeeddemo.events.tasks.base.EventTask;

public abstract class Contract implements Parcelable {
    abstract public EventTask newTask(Context context);

    public Contract() {
    }
}
