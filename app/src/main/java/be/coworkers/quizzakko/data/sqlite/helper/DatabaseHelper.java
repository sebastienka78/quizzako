package be.coworkers.quizzakko.data.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import be.coworkers.quizzakko.data.model.dao.Quizz;
import be.coworkers.quizzakko.data.model.dao.QuizzTheme;
import be.coworkers.quizzakko.data.model.dao.User;
import be.coworkers.quizzakko.data.model.dao.question.DateQuestion;
import be.coworkers.quizzakko.data.model.dao.question.IQuestion;
import be.coworkers.quizzakko.data.model.dao.question.ListQuestion;
import be.coworkers.quizzakko.data.model.dao.question.MultipleChoiceQuestion;
import be.coworkers.quizzakko.data.model.dao.question.NumericQuestion;
import be.coworkers.quizzakko.data.model.dao.question.OrderingQuestion;
import be.coworkers.quizzakko.data.model.dao.question.Question;
import be.coworkers.quizzakko.data.model.dao.question.TrueFalseQuestion;
import be.coworkers.quizzakko.data.model.dao.question.WordQuestion;
import be.coworkers.quizzakko.data.sqlite.table.QuestionTable;
import be.coworkers.quizzakko.data.sqlite.table.QuizzTable;
import be.coworkers.quizzakko.data.sqlite.table.QuizzThemeTable;
import be.coworkers.quizzakko.data.sqlite.table.ThemeTable;
import be.coworkers.quizzakko.data.sqlite.table.UserTable;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Quizzakko.db";

    /**
     * constructor
     * @param context origin
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Create all database tables
     * @param db db file reference
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_TABLE_USERS);
        db.execSQL(QuizzTable.CREATE_TABLE_QUIZZ);
        db.execSQL(QuestionTable.CREATE_TABLE_QUESTIONS);
        db.execSQL(ThemeTable.CREATE_TABLE_THEME);
        db.execSQL(QuizzThemeTable.CREATE_TABLE_QUIZZ_THEME);
    }

    /**
     * Delete all database tables et recreate database
     * @param db            db file reference
     * @param oldVersion    old id version
     * @param newVersion    new id version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_TABLE_NAME");
        db.execSQL("DROP TABLE IF EXISTS QUESTION_TABLE_NAME");
        db.execSQL("DROP TABLE IF EXISTS QUIZZ_TABLE_NAME");
        db.execSQL("DROP TABLE IF EXISTS QUIZZ_THEME_TABLE_NAME");
        db.execSQL("DROP TABLE IF EXISTS THEME_TABLE_NAME");
        onCreate(db);
    }

    /**
     * Get All Quizz entry from users table
     * @return  ArrayList<Quizz>
     */
    public ArrayList<User> getAllUser() {
        ArrayList<User> user_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + UserTable.USER_TABLE_NAME, null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            User newUser = new User();
            newUser.setId(res.getInt(res.getColumnIndex(UserTable.USER_COLUMN_ID)));
            newUser.setUserId(res.getInt(res.getColumnIndex(UserTable.USER_COLUMN_USERID)));
            newUser.setUsername(res.getString(res.getColumnIndex(UserTable.USER_COLUMN_USERNAME)));
            user_list.add(newUser);
            res.moveToNext();
        }
        res.close();
        return user_list;
    }

    /**
     * Insert user into users table
     * @param username      user username
     * @param email         user email
     * @param password      user password
     * @return boolean
     */
    public boolean insertUser(String username, long userId, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.USER_COLUMN_USERNAME, username);
        contentValues.put(UserTable.USER_COLUMN_USERID, userId);
        if(email != null) contentValues.put(UserTable.USER_COLUMN_EMAIL, email);
        if(password != null) contentValues.put(UserTable.USER_COLUMN_PASSWORD, password);
        db.insert(UserTable.USER_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    /**
     * Update user entry from users table
     * @param username  user username
     * @param email     user email
     * @param password  user password
     * @return boolean  request status
     */
    public boolean updateUser (String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(username != null) contentValues.put(UserTable.USER_COLUMN_USERNAME, username);
        if(email != null) contentValues.put(UserTable.USER_COLUMN_EMAIL, email);
        if(password != null) contentValues.put(UserTable.USER_COLUMN_PASSWORD, password);
        db.update(UserTable.USER_TABLE_NAME, contentValues, "username = ? ", new String[]{username});
        return true;
    }

    /**
     * Delete user from users table
     * @param id USER id to delete
     * @return Integer User id has been deleted
     */
    public Integer deleteUser (long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UserTable.USER_TABLE_NAME,
                "id = ? ",
                new String[] { Long.toString(id) });
    }

    /**
     * Get All Quizz entry from quizz table
     * @param userId long user ID
     * @return  ArrayList<Quizz>
     */
    public ArrayList<Quizz> getAllQuizzFromUser(long userId) {
        ArrayList<Quizz> quizz_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
    //    Cursor res =  db.rawQuery("select * from " + QuizzTable.QUIZZ_TABLE_NAME + " where " + QuizzTable.QUIZZ_COLUMN_ID + " = ?", new String[(int) userId]);
        Cursor res =  db.rawQuery( "select * from " + QuizzTable.QUIZZ_TABLE_NAME, null);

        res.moveToFirst();
        while(!res.isAfterLast()){
            Quizz newQuizz = new Quizz();
            newQuizz.setId(res.getInt(res.getColumnIndex(QuizzTable.QUIZZ_COLUMN_ID)));
            newQuizz.setTitle(res.getString(res.getColumnIndex(QuizzTable.QUIZZ_COLUMN_TITLE)));
            newQuizz.setUserId(res.getInt(res.getColumnIndex(QuizzTable.QUIZZ_COLUMN_USERID)));
            newQuizz.setCreationDate(res.getString(res.getColumnIndex(QuizzTable.QUIZZ_COLUMN_CREATIONDATE)));
            newQuizz.questions = getAllQuestion(newQuizz.getId());
            newQuizz.themes = getAllQuizzTheme(newQuizz.getId());
            quizz_list.add(newQuizz);
            res.moveToNext();
        }
        res.close();
        return quizz_list;
    }

    /**
     * Insert new Quizz entry into quizz table
     * @param quizz the Quizz object to insert into database
     * @return long the quizz ID in the database
     */
    public long insertQuizz(Quizz quizz) {
        long themeId, quizzId;
        // Get current date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        // Insert new entry into Quizz table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues quizzContentValues = new ContentValues();
        quizzContentValues.put(QuizzTable.QUIZZ_COLUMN_TITLE, quizz.getTitle());
        quizzContentValues.put(QuizzTable.QUIZZ_COLUMN_USERID, quizz.getUserId());
        quizzContentValues.put(QuizzTable.QUIZZ_COLUMN_CREATIONDATE, formattedDate);
        quizzId = db.insert(QuizzTable.QUIZZ_TABLE_NAME, null, quizzContentValues);


        for(QuizzTheme theme : quizz.themes ){
            // Insert new entry into Theme table
            insertQuizzTheme(theme, quizzId);
        }

        for(Question question : quizz.questions ){
            insertQuestion(question, quizzId);
        }
        return quizzId;
    }

    /**
     * insert new QuizzTheme into quizzTheme table
     * @param theme The QuizzTheme to save into database
     * @return the id of the new QuizzzTheme's row in database
     */
    public long insertQuizzTheme(QuizzTheme theme, long quizzId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long quizzThemeId = -1;
        long themeId;
        if(theme.getThemeId() == 0) {
            ContentValues themeContentValues = new ContentValues();
            themeContentValues.put(ThemeTable.THEME_COLUMN_TITLE, theme.getTitle());
            themeId = db.insert(ThemeTable.THEME_TABLE_NAME, null, themeContentValues);
        }else
            themeId = theme.getThemeId();
        // Insert new entry into QuizzTheme table
        ContentValues quizzThemeContentValues = new ContentValues();
        quizzThemeContentValues.put(QuizzThemeTable.QUIZZ_THEME_COLUMN_THEME_ID, themeId);
        quizzThemeContentValues.put(QuizzThemeTable.QUIZZ_THEME_COLUMN_QUIZZ_ID, quizzId);
        db.insert(QuizzThemeTable.QUIZZ_THEME_TABLE_NAME, null, quizzThemeContentValues);
        return quizzThemeId;
    }

    /**
     * Insert new Question into questions table
     * @param question the Question to insert into database
     * @return questionId id of the new question's row in database
     */
    public long insertQuestion(Question question, long quizzId){
        SQLiteDatabase db = this.getWritableDatabase();
        long questionId = -1;
        // Insert new entry into Question table
        ContentValues questionContentValues = new ContentValues();
        questionContentValues.put(QuestionTable.QUESTION_COLUMN_TITLE, question.getQuestion());
        questionContentValues.put(QuestionTable.QUESTION_COLUMN_ANSWER, ((IQuestion)question).getStringAnswer());
        questionContentValues.put(QuestionTable.QUESTION_COLUMN_TYPE, question.getType());
        questionContentValues.put(QuestionTable.QUESTION_COLUMN_QUIZZID, quizzId);
        questionId = db.insert(QuestionTable.QUESTION_TABLE_NAME, null, questionContentValues);
        return questionId;
    }

    /**
     * Get All Question entry from questions table
     * @param quizzId long quizz ID
     * @return ArrayList<Question>
     */
    public ArrayList<Question> getAllQuestion(long quizzId) {
        try {
            ArrayList<Question> question_list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from "+ QuestionTable.QUESTION_TABLE_NAME +" where "+ QuestionTable.QUESTION_COLUMN_QUIZZID +"= ?", new String[]{Long.toString(quizzId)});
            res.moveToFirst();
            int questionType;
            while (!res.isAfterLast()) {
                questionType = res.getInt(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TYPE));
                Question question = null;
                DateFormat format = DateFormat.getDateInstance();
                switch (questionType) {
                    case Question.WORD_QUESTION_TYPE:
                        question = new WordQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        ((WordQuestion) question).setAnswer(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_ANSWER)));
                        break;
                    case Question.NUMERIC_QUESTION_TYPE:
                        question = new NumericQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        ((NumericQuestion) question).setAnswer(Double.parseDouble(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_ANSWER))));
                        break;
                    case Question.TRUEFALSE_QUESTION_TYPE:
                        question = new TrueFalseQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        ((TrueFalseQuestion) question).setAnswer(Boolean.getBoolean(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_ANSWER))));
                        break;
                    case Question.DATE_QUESTION_TYPE:
                        question = new DateQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        try {
                            ((DateQuestion) question).setAnswer(format.parse(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_ANSWER))));
                        } catch (ParseException pe) {
                            Log.i("quizzakko error", pe.toString());
                        }
                        break;
                    case Question.LIST_QUESTION_TYPE:
                        question = new ListQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        //((ListQuestion)question).setAnswer(Double.parseDouble(res.getString(res.getColumnIndex(QUESTION_COLUMN_ANSWER))));
                        break;
                    case Question.ORDERING_QUESTION_TYPE:
                        question = new OrderingQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        //((OrderingQuestion)question).setAnswer(Double.parseDouble(res.getString(res.getColumnIndex(QUESTION_COLUMN_ANSWER))));
                        break;
                    case Question.MULTIPLECHOICE_QUESTION_TYPE:
                        question = new MultipleChoiceQuestion(res.getString(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TITLE)));
                        //((MultipleChoiceQuestion)question).setAnswer(Double.parseDouble(res.getString(res.getColumnIndex(QUESTION_COLUMN_ANSWER))));
                        break;
                    default:
                        break;
                }
                question.setQuestionId(res.getInt(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_ID)));
                question.setType(res.getInt(res.getColumnIndex(QuestionTable.QUESTION_COLUMN_TYPE)));
                question.setQuizzId(quizzId);
                question_list.add(question);
                res.moveToNext();
            }
            res.close();
            return question_list;
        }catch(NullPointerException ne){
            Log.i("quizzakko error", ne.toString());
        }
        return null;
    }

    /**
     * Get All QuizzTheme entry from quizzTheme table
     * @param quizzId long quizz ID
     * @return ArrayList<QuizzTheme>
     */
    public ArrayList<QuizzTheme> getAllQuizzTheme(long quizzId) {
        ArrayList<QuizzTheme> quizzTheme_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT *"
                + " FROM " + QuizzThemeTable.QUIZZ_THEME_TABLE_NAME + ", " + ThemeTable.THEME_TABLE_NAME
                + " WHERE "+ QuizzThemeTable.QUIZZ_THEME_TABLE_NAME +"."+ QuizzThemeTable.QUIZZ_THEME_COLUMN_THEME_ID + " = " + ThemeTable.THEME_TABLE_NAME +"."+ ThemeTable.THEME_COLUMN_ID
                + " AND " + QuizzThemeTable.QUIZZ_THEME_TABLE_NAME +"."+ QuizzThemeTable.QUIZZ_THEME_COLUMN_QUIZZ_ID + " = " + quizzId;
        Cursor res = db.rawQuery(query, null);
        if(res.moveToFirst()) {
            while (!res.isAfterLast()) {
                QuizzTheme newQuizzTheme = new QuizzTheme();
                int index = res.getInt(res.getColumnIndex(QuizzThemeTable.QUIZZ_THEME_COLUMN_THEME_ID));
                newQuizzTheme.setThemeId(index);
                newQuizzTheme.setTitle(res.getString(res.getColumnIndex(ThemeTable.THEME_COLUMN_TITLE)));
                quizzTheme_list.add(newQuizzTheme);
                res.moveToNext();
            }
        }else{
            Log.i("QUIZZAKKO", "EMPTY RESULT FOR QUIZZTHEME QUERY");
        }
        res.close();
        return quizzTheme_list;
    }

    /**
     * Export database .db file on SdCard
     */
    public void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "be.coworkers.quizzakko" +"/databases/"+DATABASE_NAME;
        String backupDBPath = DATABASE_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            //Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete database and recreate it
     *
     */
    public void deleteDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.USER_TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.QUESTION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizzTable.QUIZZ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizzThemeTable.QUIZZ_THEME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ThemeTable.THEME_TABLE_NAME);
        onCreate(db);
    }
}