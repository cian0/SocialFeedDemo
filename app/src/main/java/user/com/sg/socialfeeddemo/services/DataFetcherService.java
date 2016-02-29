package user.com.sg.socialfeeddemo.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import user.com.sg.socialfeeddemo.constants.Keys;
import user.com.sg.socialfeeddemo.database.datalayer.FeedsDal;
import user.com.sg.socialfeeddemo.database.model.Feed;
import user.com.sg.socialfeeddemo.database.model.Profile;
import user.com.sg.socialfeeddemo.events.EventConstants;
import user.com.sg.socialfeeddemo.events.EventManager;
import user.com.sg.socialfeeddemo.utils.MockDataParser;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class DataFetcherService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public DataFetcherService() {
        super(Keys.Services.DATA_FETCHER);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // if this service is started, it will merely get the mock data and parse them.
        // do the actual parsing here.

        MockDataParser.parseData(getApplicationContext());

        FeedsDal dal = (FeedsDal) FeedsDal.getInstance(getApplicationContext());
        Collection<Feed> feeds;
//        ArrayList
        try {
            feeds = dal.getFeedsByUser(new Profile(2));
            Log.i("feeds", " length " + feeds.size());
//            feeds.
            EventManager.getInstance().broadcast(getApplicationContext(), EventConstants.PARSE_FINISHED);
        } catch (SQLException e) {
            EventManager.getInstance().broadcast(getApplicationContext(), EventConstants.PARSE_FAILED);
            e.printStackTrace();
        }



    }
}
