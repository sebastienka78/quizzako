package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

/**
 *
 * Created by sebastienk on 11-Feb-16.
 */
public class WordQuestion extends Question implements IQuestion {

    private String answer;
    private String questionText;

    public WordQuestion() {}

    public WordQuestion(String question){
        setQuestion(question);
    }

    public WordQuestion(String question, String answer){
        setQuestion(question);
        setAnswer(answer);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answervalue) {
        this.answer = answervalue;
    }

    @Override
    public void setQuestion(String questionValue) { this.questionText = questionValue; }

    @Override
    public String getQuestion() { return this.questionText; }

    @Override
    public String getStringAnswer() { return answer; }

    @Override
    public void setAnswerFromString(String answerValue) { setAnswer(answerValue); }

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.answer);
        dest.writeString(this.questionText);
    }

    protected WordQuestion(Parcel in) {
        super(in);
        this.answer = in.readString();
        this.questionText = in.readString();
    }

    public static final Creator<WordQuestion> CREATOR = new Creator<WordQuestion>() {
        public WordQuestion createFromParcel(Parcel source) {
            return new WordQuestion(source);
        }

        public WordQuestion[] newArray(int size) {
            return new WordQuestion[size];
        }
    };
}