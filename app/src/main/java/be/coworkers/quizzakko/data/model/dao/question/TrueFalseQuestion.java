package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

/**
 *
 * Created by sebastienk on 11-Feb-16.
 */
public class TrueFalseQuestion extends Question  implements IQuestion{

    private boolean answer;
    private String questionText;

    public TrueFalseQuestion(){ }

    public TrueFalseQuestion(String question){
        setQuestion(question);
    }

    public TrueFalseQuestion(String question, boolean answer){
        setQuestion(question);
        setAnswer(answer);
    }

    public boolean isAnswer() { return answer; }

    public void setAnswer(boolean answer) { this.answer = answer; }


    @Override
    public void setQuestion(String questionValue) { this.questionText = questionValue; }

    @Override
    public String getQuestion() { return this.questionText; }

    @Override
    public String getStringAnswer() { return answer ? "1" : "0"; }

    @Override
    public void setAnswerFromString(String answerValue) { setAnswer(!answerValue.equals("0")); }

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
        dest.writeByte(answer ? (byte) 1 : (byte) 0);
        dest.writeString(this.questionText);
    }

    protected TrueFalseQuestion(Parcel in) {
        super(in);
        this.answer = in.readByte() != 0;
        this.questionText = in.readString();
    }

    public static final Creator<TrueFalseQuestion> CREATOR = new Creator<TrueFalseQuestion>() {
        public TrueFalseQuestion createFromParcel(Parcel source) {
            return new TrueFalseQuestion(source);
        }

        public TrueFalseQuestion[] newArray(int size) {
            return new TrueFalseQuestion[size];
        }
    };
}
