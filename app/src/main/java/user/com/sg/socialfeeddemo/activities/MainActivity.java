package user.com.sg.socialfeeddemo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import user.com.sg.socialfeeddemo.R;
import user.com.sg.socialfeeddemo.events.OnEventReceivedListener;
import user.com.sg.socialfeeddemo.services.DataFetcherService;
import user.com.sg.socialfeeddemo.utils.AppSessionManager;
import user.com.sg.socialfeeddemo.utils.DialogFactory;
import user.com.sg.socialfeeddemo.utils.ServiceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog dialog = (ProgressDialog) DialogFactory.show(DialogFactory.Type.LOADING, this);

        /**
         * We just clear the session data and refresh anew whenever the app is closed and opened
         */
        AppSessionManager.getInstance(this).setupFirsttime(this, new Runnable() {
            @Override
            public void run() {
                // setup finished.
                dialog.dismiss();
            }
        });
    }

}
