package be.coworkers.quizzakko.data.model.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import be.coworkers.quizzakko.data.model.dao.question.Question;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */

public class Quizz implements Parcelable {


    private int id;
    private int userId;
    private String title;
    private String creationDate;

    public ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<QuizzTheme> themes = new ArrayList<>();

    public Quizz(){}

    public Quizz(int id, int userId, String title, String creationDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.creationDate = creationDate;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void addThemesByString(String themesString) {
        String [] themesSplitted = themesString.split(",");
        for(String title : themesSplitted) {
            QuizzTheme quizzTheme =  new QuizzTheme(title);
            themes.add(quizzTheme);
        }
    }

    @Override
    public String toString() {

        return "Quizz [id=" + id + ", title=" + title + "]";
    }

    protected Quizz(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        title = in.readString();
        creationDate = in.readString();
        if (in.readByte() == 0x01) {
            questions = new ArrayList<>();
            in.readList(questions, Question.class.getClassLoader());
        } else {
            questions = null;
        }
        if (in.readByte() == 0x01) {
            themes = new ArrayList<>();
            in.readList(themes, QuizzTheme.class.getClassLoader());
        } else {
            themes = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(title);
        dest.writeString(creationDate);
        if (questions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(questions);
        }
        if (themes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(themes);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<Quizz> CREATOR = new Creator<Quizz>() {
        @Override
        public Quizz createFromParcel(Parcel in) {
            return new Quizz(in);
        }

        @Override
        public Quizz[] newArray(int size) {
            return new Quizz[size];
        }
    };
}