package be.coworkers.quizzakko.data.sqlite.table;

/**
 *
 * Created by sebastienk on 15-Feb-16.
 */
public class QuizzTable {

    // QUIZZ TABLE
    public static final String QUIZZ_TABLE_NAME = "quizz";
    public static final String QUIZZ_COLUMN_ID = "id";
    public static final String QUIZZ_COLUMN_TITLE = "title";
    public static final String QUIZZ_COLUMN_USERID = "userId";
    public static final String QUIZZ_COLUMN_CREATIONDATE = "creationDate";

    public static final String CREATE_TABLE_QUIZZ = "CREATE TABLE "
            + QUIZZ_TABLE_NAME + "(" + QUIZZ_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + QUIZZ_COLUMN_TITLE + " TEXT,"
            + QUIZZ_COLUMN_CREATIONDATE + " TEXT,"
            + QUIZZ_COLUMN_USERID + " INTEGER,"
            + " FOREIGN KEY (" + QUIZZ_COLUMN_USERID +") REFERENCES "+ UserTable.USER_TABLE_NAME +"("+ UserTable.USER_COLUMN_ID +"));";
}
