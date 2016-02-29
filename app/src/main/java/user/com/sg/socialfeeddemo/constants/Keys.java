package user.com.sg.socialfeeddemo.constants;

/**
 * Created by ianicasiano on 2/26/16.
 */
public abstract class Keys {
    public abstract class Services {
        public static final String DATA_FETCHER = "data_fetcher";
    }
    public abstract class Table {
        public static final String PROFILE = "profile";
        public static final String FEED = "feed";
        public static final String COMMENT = "comment";
    }
    public abstract class Column {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PHOTO_PATH = "photo_path";
        public static final String CREATION_DATE = "creation_date";
        public static final String CAPTION = "caption";
        public static final String COMMENT_TEXT = "comment_text";
        public static final String COMMENT_COUNT = "comment_count";
        public static final String LIKES_COUNT = "likes_count";
    }
    public abstract class JsonKeys {
        public static final String FEED_ID = "feed_id";
        public static final String COMMENTS = "comments";
        public static final String USER_ID = "user_id";
        public static final String COMMENT_ID = "comment_id";
        public static final String COMMENT_TEXT = "comment_text";
        public static final String USER_PROFILE_ID = "user_profile_id";
        public static final String DATE_CREATED = "date_created";
        public static final String CAPTION = "caption";
        public static final String PHOTO = "photo";
        public static final String NUM_LIKES = "num_likes";
        public static final String NUM_COMMENTS = "num_comments";
        public static final String NAME = "name";
        public static final String PHOTO_PATH = "photo_path";
        public static final String ID = "id";
    }
}
