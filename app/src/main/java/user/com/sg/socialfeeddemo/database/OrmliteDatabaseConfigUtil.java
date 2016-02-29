package user.com.sg.socialfeeddemo.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import user.com.sg.socialfeeddemo.database.model.Comment;
import user.com.sg.socialfeeddemo.database.model.Feed;
import user.com.sg.socialfeeddemo.database.model.Profile;

/**
 * Created by ianicasiano on 2/26/16.
 *
 * Standalone application, this is run separately.
 * Contains the generator of config
 */
public class OrmliteDatabaseConfigUtil extends OrmLiteConfigUtil {
    public static final Class<?>[] classes = new Class[] {Profile.class, Comment.class, Feed.class};

    /**
     * Given that this is a separate program from the android app, we have to use
     * a static main java method to create the configuration file.
     * @param args
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {

        String currDirectory = "user.dir";

        String configPath = "/app/src/main/res/raw/ormlite_config.txt";

        /**
         * Gets the project root directory
         */
        String projectRoot = System.getProperty(currDirectory);

        /**
         * Full configuration path includes the project root path, and the location
         * of the ormlite_config.txt file appended to it
         */
        String fullConfigPath = projectRoot + configPath;

        File configFile = new File(fullConfigPath);

        /**
         * In the a scenario where we run this program serveral times, it will recreate the
         * configuration file each time with the updated configurations.
         */
        if(configFile.exists()) {
            configFile.delete();
            configFile = new File(fullConfigPath);
        }

        /**
         * writeConfigFile is a util method used to write the necessary configurations
         * to the ormlite_config.txt file.
         */
        writeConfigFile(configFile, classes);
    }
}
