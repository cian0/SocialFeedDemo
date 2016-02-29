package user.com.sg.socialfeeddemo.events;

import user.com.sg.socialfeeddemo.events.contracts.base.Contract;

public interface OnGlobalEventReceivedListener  extends EventListener<Contract>{
    void onEventReceived(String event, Contract data);
}
