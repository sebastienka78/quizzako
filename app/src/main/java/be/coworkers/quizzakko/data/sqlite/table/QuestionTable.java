package be.coworkers.quizzakko.data.sqlite.table;

/**
 *
 * Created by sebastienk on 15-Feb-16.
 */
public class QuestionTable {

    // QUESTION TABLE
    public static final String QUESTION_TABLE_NAME = "questions";
    public static final String QUESTION_COLUMN_ID = "id";
    public static final String QUESTION_COLUMN_TITLE = "title";
    public static final String QUESTION_COLUMN_TYPE = "type";
    public static final String QUESTION_COLUMN_ANSWER = "answer";
    public static final String QUESTION_COLUMN_QUIZZID = "quizzId";

    public static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE "
            + QUESTION_TABLE_NAME + "(" + QUESTION_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + QUESTION_COLUMN_TITLE + " TEXT,"
            + QUESTION_COLUMN_ANSWER + " TEXT,"
            + QUESTION_COLUMN_TYPE + " INTEGER,"
            + QUESTION_COLUMN_QUIZZID + " INTEGER,"
            + " FOREIGN KEY (" + QUESTION_COLUMN_QUIZZID + ") REFERENCES " + QuizzTable.QUIZZ_TABLE_NAME + "(" + QuizzTable.QUIZZ_COLUMN_ID + "));";
}
