package user.com.sg.socialfeeddemo.database.datalayer;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import user.com.sg.socialfeeddemo.constants.Keys;
import user.com.sg.socialfeeddemo.database.SocialFeedsHelper;
import user.com.sg.socialfeeddemo.database.model.Feed;
import user.com.sg.socialfeeddemo.database.model.Profile;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class ProfileDal extends DataLayer<Profile, Integer> {
    public static DataLayer getInstance(Context context) {
        if (checkIfNull()) {
            ourInstance = new ProfileDal(context);
        }

        return ourInstance;
    }

    private ProfileDal(Context context) {
        super(context);
    }

    @Override
    void initialize() {
        try {
            dao = openHelper.getDao(Profile.class);
            queryBuilder = dao.queryBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLException!");
        }
    }

    public Profile getProfile(int id) throws SQLException {
        Where where = queryBuilder.where();
        where.eq(Keys.Column.ID, id);
        Profile profile = dao.queryForId(id);
        return profile;
    }
}
