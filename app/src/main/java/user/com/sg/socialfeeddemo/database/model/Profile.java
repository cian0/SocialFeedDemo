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
@DatabaseTable(tableName= Keys.Table.PROFILE)
public class Profile implements IParser{

    @DatabaseField(canBeNull = false, columnName = Keys.Column.ID, id = true)
    private Integer id;
    @DatabaseField(canBeNull = false, columnName = Keys.Column.NAME)
    private String name;
    @DatabaseField(canBeNull = true, columnName = Keys.Column.PHOTO_PATH)
    private String photoPath;
    @ForeignCollectionField
    private Collection<Feed> feeds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Collection<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(Collection<Feed> feeds) {
        this.feeds = feeds;
    }

    public Profile() {

    }

    public Profile(int id) {
        this.id = id;
    }

    @Override
    public void parse(JSONObject object) {
        try {
            this.id = object.getInt(Keys.JsonKeys.ID);
            this.photoPath = object.getString(Keys.JsonKeys.PHOTO_PATH);
            this.name = object.getString(Keys.JsonKeys.NAME);

        } catch (JSONException e) {
            // all json errors must be fixed before allowing to get parsed on the app. Thus the runtime exception
            throw new RuntimeException(e);
        }
    }
}
