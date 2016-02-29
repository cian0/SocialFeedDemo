package user.com.sg.socialfeeddemo.database.datalayer.interfaces;

import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by ianicasiano on 2/27/16.
 *
 * interface to be used by all Data Layers for common CRUD operations.
 */
public interface Persistable<T, L> {
    void insertOrUpdate(T entry) throws SQLException;
    void insertOrUpdateAll(Collection<T> entries) throws SQLException;
    void delete(T entry) throws SQLException;
    void deleteAll(Collection<T> entries) throws SQLException;
    T fetchById(L id) throws SQLException;
    Collection<T> fetchWithQuery(Where<T, L> where) throws SQLException;
}
