package user.com.sg.socialfeeddemo.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import user.com.sg.socialfeeddemo.constants.Keys;
import user.com.sg.socialfeeddemo.database.model.interfaces.IParser;

/**
 * Created by ianicasiano on 2/26/16.
 */
@DatabaseTable(tableName= Keys.Table.COMMENT)
public class Comment implements IParser {
    @DatabaseField(canBeNull = false, columnName = Keys.Column.ID, id = true)
    private Integer id;
    @DatabaseField (foreign = true, foreignAutoRefresh = true, columnName = Keys.Table.PROFILE)
    private Profile commentator;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.COMMENT_TEXT)
    private String comment;
    @DatabaseField (foreign = true, foreignAutoRefresh = true, columnName = Keys.Table.FEED)
    private Feed feed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return commentator;
    }

    public void setProfile(Profile profile) {
        this.commentator = profile;
    }

    public String getCommentText() {
        return comment;
    }

    public void setCommentText(String commentText) {
        this.comment = commentText;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    @Override
    public void parse(JSONObject object) {
        try {
            this.id = object.getInt(Keys.JsonKeys.ID);
            this.comment = object.getString(Keys.JsonKeys.COMMENT_TEXT);

        } catch (JSONException e) {
            // all json errors must be fixed before allowing to get parsed on the app. Thus the runtime exception
            throw new RuntimeException(e);
        }
    }
}
