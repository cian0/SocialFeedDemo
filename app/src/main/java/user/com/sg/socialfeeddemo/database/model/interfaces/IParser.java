package user.com.sg.socialfeeddemo.database.model.interfaces;

import org.json.JSONObject;

/**
 * Created by ianicasiano on 2/26/16.
 *
 * Interface that lets models parse a json object and copy its properties.
 */
public interface IParser {

    /**
     * method to parse an object and copy its contents.
     * @param object the json object to parse.
     */
    void parse(JSONObject object);
}
