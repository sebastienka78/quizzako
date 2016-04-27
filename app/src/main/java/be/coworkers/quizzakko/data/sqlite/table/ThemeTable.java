package be.coworkers.quizzakko.data.sqlite.table;

/**
 *
 * Created by sebastienk on 15-Feb-16.
 */
public class ThemeTable {

    // THEME TABLE
    public static final String THEME_TABLE_NAME = "theme";
    public static final String THEME_COLUMN_ID = "id";
    public static final String THEME_COLUMN_TITLE = "title";

    public static final String CREATE_TABLE_THEME = "CREATE TABLE "
            + THEME_TABLE_NAME + "(" + THEME_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + THEME_COLUMN_TITLE + " TEXT" + ")";


}
