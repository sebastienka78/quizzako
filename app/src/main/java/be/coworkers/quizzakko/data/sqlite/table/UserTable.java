package be.coworkers.quizzakko.data.sqlite.table;

/**
 *
 * Created by sebastienk on 15-Feb-16.
 */
public class UserTable {

    // USER TABLE
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_USERID = "userId";

    // Table Create Statements
    public static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + USER_TABLE_NAME + "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + USER_COLUMN_USERNAME + " TEXT,"
            + USER_COLUMN_EMAIL + " TEXT,"
            + USER_COLUMN_PASSWORD + " TEXT,"
            + USER_COLUMN_USERID + " INTEGER" + ")";
}
