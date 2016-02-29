package user.com.sg.socialfeeddemo.events;

public interface EventListener<DATA> {
    void onEventReceived(String event, DATA data);
    EventReceiver getEventReceiver();
    EventReceiver initializeEventReceiver();
}
