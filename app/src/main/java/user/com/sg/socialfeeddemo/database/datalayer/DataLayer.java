package user.com.sg.socialfeeddemo.database.datalayer;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import user.com.sg.socialfeeddemo.database.SocialFeedsHelper;
import user.com.sg.socialfeeddemo.database.datalayer.interfaces.Persistable;

/**
 * Created by ianicasiano on 2/27/16.
 */
public abstract class DataLayer<T, L> implements Persistable<T, L>{

    public static final String TAG = "DBBabyActivityDal";
    protected static DataLayer ourInstance;
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected QueryBuilder<T, L> queryBuilder;
    protected Dao<T, L> dao;
    protected Context context;
    protected SocialFeedsHelper openHelper = null;

    protected static boolean checkIfNull() {
        if (ourInstance == null) {
            return true;
        }
        if (((DataLayer)ourInstance).queryBuilder == null) {
            return true;
        }

        if (((DataLayer)ourInstance).dao == null) {
            return true;
        }
        return false;
    }

    protected DataLayer(Context context) {
        this.context = context;
        openHelper = OpenHelperManager.getHelper(context,
                SocialFeedsHelper.class);

        initialize();
    }

    abstract void initialize();

    @Override
    public void insertOrUpdate(T entry) throws SQLException {
        dao.createOrUpdate(entry);
    }

    @Override
    public void insertOrUpdateAll(Collection<T> entries) throws SQLException {
        for (T entry: entries) {
            insertOrUpdate(entry);
        }
    }

    @Override
    public void delete(T entry) throws SQLException {
        dao.delete(entry);
    }

    @Override
    public void deleteAll(Collection<T> entries) throws SQLException {
        dao.delete(entries);
    }

    @Override
    public T fetchById(L id) throws SQLException {
        return dao.queryForId(id);
    }

    @Override
    public Collection<T> fetchWithQuery(Where<T, L> where) throws SQLException {
        return dao.query(where.prepare());
    }
}
