package user.com.sg.socialfeeddemo.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import user.com.sg.socialfeeddemo.R;
import user.com.sg.socialfeeddemo.database.model.Comment;
import user.com.sg.socialfeeddemo.database.model.Feed;
import user.com.sg.socialfeeddemo.database.model.Profile;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class MockDataParser {
    public static void parseData(Context context) {
        JSONArray profilesJson = FileUtils.rawResourceToJsonArray(context, R.raw.profiles);
        JSONArray commentsJson = FileUtils.rawResourceToJsonArray(context, R.raw.comments);
        JSONArray feedsJson = FileUtils.rawResourceToJsonArray(context, R.raw.feeds);
        JSONObject configJson = FileUtils.rawResourceToJson(context, R.raw.config);

        ArrayList<Profile> profiles = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        ArrayList<Feed> feeds = new ArrayList<>();

        if (profilesJson == null) {
            profilesJson = new JSONArray();
        }
        if (commentsJson == null) {
            commentsJson = new JSONArray();
        }
        if (feedsJson == null) {
            feedsJson = new JSONArray();
        }
        if (configJson == null) {
            configJson = new JSONObject();
        }

        int i = 0, j = 0;

        for (i = 0, j = profilesJson.length(); i < j; i++) {
            Profile profile = new Profile();
            try {
                profile.parse(profilesJson.getJSONObject(i));
                profiles.add(profile);
            } catch (JSONException e) {
                // ignore unparseables for now
                continue;
            }
        }
        for (i = 0, j = commentsJson.length(); i < j; i++) {
            Comment comment = new Comment();
            try {
                comment.parse(commentsJson.getJSONObject(i));
                comments.add(comment);
            } catch (JSONException e) {
                // ignore unparseables for now
                continue;
            }

        }

        for (i = 0, j = feedsJson.length(); i < j; i++) {
            Feed feed = new Feed();
            try {
                feed.parse(feedsJson.getJSONObject(i));
                feeds.add(feed);
            } catch (JSONException e) {
                // ignore unparseables for now
                continue;
            }

        }
    }
}
