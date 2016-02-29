package user.com.sg.socialfeeddemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ianicasiano on 2/26/16.
 */
public class Session {
    private static Context _context;
    private final Gson mGson;
    protected final SharedPreferences mAuthPref;
    protected final SharedPreferences.Editor mEditor;

    public Session(Context context) {
        _context = context;
        String prefName;
        prefName = "AppAuth."
                .concat("mobile.imfree.com.babytalk")
                .concat(".session");


        mAuthPref = context.getSharedPreferences(prefName, Context.MODE_MULTI_PROCESS   );
        mGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        mEditor = mAuthPref.edit();
    }

    protected SharedPreferences getSessionHolder() {
        return mAuthPref;
    }
    /**
     *
     * @param key - key of the desired data.
     * @return a string in json format
     */
    protected synchronized <T> T get(String key, Class<T> classOfT) {
        String json = mAuthPref.getString(key, null);
        return  json==null ? null : mGson.fromJson(json, classOfT);
    }

    /**
     * Store data to session temporarily storage
     * @param key - key used to identify the data stored data
     * @param value - json string format string.
     * @param classOfT - Object Class Type
     * return type of object to return.
     */
    protected synchronized <T> void set(String key, T value, Class<T> classOfT) {
        SharedPreferences.Editor editor = mAuthPref.edit();
        String json = mGson.toJson(value, classOfT);
        editor.putString(key, json);
        editor.apply();
    }
    /**
     * Remove data from temporary session storage
     * @param key - key of the data to be removed from session
     */
    protected synchronized void remove(String key) {
        SharedPreferences.Editor editor = mAuthPref.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * Clear the whole temporary storage.
     */
    protected synchronized void clear() {
        mAuthPref.edit().clear().commit();
    }

}
