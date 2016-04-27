package be.coworkers.quizzakko.data.sqlite.table;

/**
 *
 * Created by sebastienk on 15-Feb-16.
 */
public class QuizzThemeTable {

    // QUIZZ_THEME TABLE
    public static final String QUIZZ_THEME_TABLE_NAME = "quizzTheme";
    public static final String QUIZZ_THEME_COLUMN_ID = "id";
    public static final String QUIZZ_THEME_COLUMN_THEME_ID = "themeId";
    public static final String QUIZZ_THEME_COLUMN_QUIZZ_ID = "quizzId";

    public static final String CREATE_TABLE_QUIZZ_THEME = "CREATE TABLE "
            + QUIZZ_THEME_TABLE_NAME + "(" + QUIZZ_THEME_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + QUIZZ_THEME_COLUMN_QUIZZ_ID + " TEXT,"
            + QUIZZ_THEME_COLUMN_THEME_ID + " TEXT" + ")";
}
