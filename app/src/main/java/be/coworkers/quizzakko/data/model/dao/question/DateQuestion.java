package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

import java.util.Date;

/**
 *
 * Created by sebastienk on 11-Feb-16.
 */
public class DateQuestion extends Question implements IQuestion {

    private Date answer;
    private String questionText;

    public DateQuestion(){ }

    public DateQuestion(String question){
        setQuestion(question);
    }

    public Date getAnswer() {
        return answer;
    }

    @Override
    public void setQuestion(String questionValue) { this.questionText = questionValue; }

    @Override
    public String getQuestion() { return this.questionText; }

    public void setAnswer(Date answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return getQuestion();
    }

    @Override
    public String getStringAnswer() {
        return null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(answer != null ? answer.getTime() : -1);
        dest.writeString(this.questionText);
    }

    protected DateQuestion(Parcel in) {
        super(in);
        long tmpAnswer = in.readLong();
        this.answer = tmpAnswer == -1 ? null : new Date(tmpAnswer);
        this.questionText = in.readString();
    }

    public static final Creator<DateQuestion> CREATOR = new Creator<DateQuestion>() {
        public DateQuestion createFromParcel(Parcel source) {
            return new DateQuestion(source);
        }

        public DateQuestion[] newArray(int size) {
            return new DateQuestion[size];
        }
    };
}
