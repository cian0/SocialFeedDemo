package user.com.sg.socialfeeddemo.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import user.com.sg.socialfeeddemo.constants.Keys;
import user.com.sg.socialfeeddemo.database.model.interfaces.IParser;
import user.com.sg.socialfeeddemo.utils.DateUtils;

/**
 * Created by ianicasiano on 2/26/16.
 */
@DatabaseTable(tableName= Keys.Table.FEED)
public class Feed implements IParser {

    @DatabaseField(canBeNull = false, columnName = Keys.Column.ID, id = true)
    private Integer id;
    @DatabaseField (foreign = true, foreignAutoRefresh = true, columnName = Keys.Table.PROFILE)
    private Profile profile;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.CAPTION)
    private String caption;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.COMMENT_COUNT)
    private int commentCount;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.CREATION_DATE)
    private Date creationDate;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.LIKES_COUNT)
    private int likesCount;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.PHOTO_PATH)
    private int photoPath;

    @ForeignCollectionField
    private Collection<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public int getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(int photoPath) {
        this.photoPath = photoPath;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public void parse(JSONObject object) {
        try {
            this.id = object.getInt(Keys.JsonKeys.FEED_ID);
            this.caption = object.getString(Keys.JsonKeys.CAPTION);
            this.commentCount = object.getInt(Keys.JsonKeys.NUM_COMMENTS);
            this.creationDate = DateUtils.toServerDateHoursMillis(object.getString(Keys.JsonKeys.DATE_CREATED));

        } catch (JSONException e) {
            // all json errors must be fixed before allowing to get parsed on the app. Thus the runtime exception
            throw new RuntimeException(e);
        } catch (ParseException e) {
            // all json errors must be fixed before allowing to get parsed on the app. Thus the runtime exception
            throw new RuntimeException(e);
        }
    }
}
