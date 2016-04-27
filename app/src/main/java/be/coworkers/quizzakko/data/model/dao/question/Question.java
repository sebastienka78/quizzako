package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

import java.io.Serializable;

import be.coworkers.quizzakko.data.model.helper.GlobalParcelable;

/**
 *
 * Created by sebastienk on 24-Jan-16.
 */
public class Question extends GlobalParcelable implements IQuestion {

    public static final int WORD_QUESTION_TYPE = 0;
    public static final int NUMERIC_QUESTION_TYPE = 1;
    public static final int TRUEFALSE_QUESTION_TYPE = 2;
    public static final int DATE_QUESTION_TYPE = 3;
    public static final int LIST_QUESTION_TYPE  = 4;
    public static final int ORDERING_QUESTION_TYPE = 5;
    public static final int MULTIPLECHOICE_QUESTION_TYPE = 6;

    private long questionId;
    private long quizzId;
    private int type;
    private String title;
    private String answer_str;

    public String getAnswer_str() {
        return answer_str;
    }

    public void setAnswer_str(String answer) {
        this.answer_str = answer;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Question() {}

    public Question(int id, int type) {
        this.questionId = id;
        this.type = type;

    }

    public long getQuestionId() { return questionId; }

    public void setQuestionId(long id) { this.questionId = id; }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public long getQuizzId() { return quizzId; }

    public void setQuizzId(long quizz_id) { quizzId = quizz_id; }

    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public void setQuestion(String questionValue) {  }

    @Override
    public void setAnswerFromString(String answerValue) {  }

    @Override
    public String getStringAnswer() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // CODE GENERATE BY Parcelable plugin


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(this.questionId);
        dest.writeLong(this.quizzId);
        dest.writeInt(this.type);
    }

    protected Question(Parcel in) {
        super(in);
        this.questionId = in.readLong();
        this.quizzId = in.readLong();
        this.type = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}