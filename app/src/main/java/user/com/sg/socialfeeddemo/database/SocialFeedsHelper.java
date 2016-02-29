package user.com.sg.socialfeeddemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import user.com.sg.socialfeeddemo.R;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class SocialFeedsHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "social_feed";
    private static final int DATABASE_VERSION = 4;

    private static final Class<?>[] classes = OrmliteDatabaseConfigUtil.classes;
    private static final String TAG = "DBOpenHelper";
    private final Context mContext;

    public SocialFeedsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                /**
                 * R.raw.ormlite_config is a reference to the ormlite_config.txt file in the
                 * /res/raw/ directory of this project
                 * */
                R.raw.ormlite_config);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates the database table
             */

            for (Class<?> clazz : classes) {
                TableUtils.createTableIfNotExists(connectionSource, clazz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "SqlException: " + e.getMessage());
            Toast.makeText(mContext, "SqlException : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {

        for (Class<?> clazz : classes) {
            try {
                TableUtils.dropTable(connectionSource, clazz, false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /**
         * Recreates the database when onUpgrade is called by the framework
         */
        onCreate(database, connectionSource);
    }
}