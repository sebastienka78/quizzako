package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

/**
 *
 * Created by sebastienk on 11-Feb-16.
 */
public class NumericQuestion extends Question implements IQuestion{

    private double answer;
    private String questionText;

    public NumericQuestion() {}

    public NumericQuestion(String question){
        setQuestion(question);
    }

    public NumericQuestion(String question, double answer){
        setQuestion(question);
        setAnswer(answer);
    }

    public double getAnswer() { return answer; }

    public void setAnswer(double answer) { this.answer = answer; }

    @Override
    public void setQuestion(String questionValue) { this.questionText = questionValue; }

    @Override
    public String getQuestion() { return this.questionText; }

    @Override
    public String getStringAnswer() { return Double.toString(answer); }

    @Override
    public void setAnswerFromString(String answerValue) {
        setAnswer(Double.parseDouble(answerValue));
    }

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
        dest.writeDouble(this.answer);
        dest.writeString(this.questionText);
    }

    protected NumericQuestion(Parcel in) {
        super(in);
        this.answer = in.readDouble();
        this.questionText = in.readString();
    }

    public static final Creator<NumericQuestion> CREATOR = new Creator<NumericQuestion>() {
        public NumericQuestion createFromParcel(Parcel source) {
            return new NumericQuestion(source);
        }

        public NumericQuestion[] newArray(int size) {
            return new NumericQuestion[size];
        }
    };
}
