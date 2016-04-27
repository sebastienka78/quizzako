package be.coworkers.quizzakko.data.preferences.helper;

import android.content.Context;
import android.content.SharedPreferences;

import be.coworkers.quizzakko.data.model.dao.User;
import be.coworkers.quizzakko.data.sqlite.helper.DatabaseHelper;

/**
 *
 *
 * Created by sebastienk on 14-Feb-16.
 */
public class SharedPreferencesHelper {

    private Context context;
    private SharedPreferences prefs = null;
    private static User defaultUser = null;

    private static SharedPreferencesHelper instance = null;


    private SharedPreferencesHelper() {
        // Exists only to defeat instantiation.
    }
    public static SharedPreferencesHelper getInstance() {
        if(instance == null) {
            instance = new SharedPreferencesHelper();
        }
        return instance;
    }

    /**
     *
     * @param contextValue context
     * @return boolean true if no problem
     */
    public boolean initApplication(Context contextValue) {
        this.context = contextValue;
        prefs = context.getSharedPreferences("be.coworkers.quizzakko", Context.MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).commit();
            prefs.edit().putBoolean("anonymous", true).commit();
            setDefaultUser();
        }
        return true;
    }

    /**
     *
     * @return boolean true if no problem
     */
    public boolean setDefaultUser(){
        if(defaultUser == null) {
            SharedPreferences userInfo = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = userInfo.edit();
            editor.putString("username", "me");
            editor.putString("id", "1");
            editor.commit();
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.insertUser("defaultUser", 1, null, null);
        }
        return true;
    }

    /**
     *
     * @return User default user is returned
     */
    @SuppressWarnings("unused")
    public User getDefaultUser(){
        if(defaultUser == null) {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            defaultUser = new User();
            defaultUser.setUsername(settings.getString("username", ""));
            defaultUser.setId(Long.getLong(settings.getString("id", "")));
        }
        return defaultUser;
    }

    /**
     *
     * @return boolean is user connected
     */
    @SuppressWarnings("unused")
    public boolean isAnonymous(){
        return prefs.getBoolean("anonymous", true);
    }
}
