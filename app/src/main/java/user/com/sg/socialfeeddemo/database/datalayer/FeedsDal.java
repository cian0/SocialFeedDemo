package user.com.sg.socialfeeddemo.database.datalayer;

import android.content.Context;

import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Collection;

import user.com.sg.socialfeeddemo.constants.Keys;
import user.com.sg.socialfeeddemo.database.model.Feed;
import user.com.sg.socialfeeddemo.database.model.Profile;

/**
 * Created by ianicasiano on 2/27/16.
 */
public class FeedsDal extends DataLayer<Feed, Integer> {
    public static DataLayer getInstance(Context context) {
        if (checkIfNull()) {
            ourInstance = new FeedsDal(context);
        }

        return ourInstance;
    }

    private FeedsDal(Context context) {
        super(context);
    }

    @Override
    void initialize() {
        try {
            dao = openHelper.getDao(Feed.class);
            queryBuilder = dao.queryBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLException!");
        }
    }

    public Collection<Feed> getFeedsByUser(Profile profile) throws SQLException {
        Where<Feed, Integer> where = queryBuilder.where();
        where.eq(Keys.Table.PROFILE, profile);
        return this.fetchWithQuery(where);
    }

}
