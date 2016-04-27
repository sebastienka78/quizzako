package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

import java.util.ArrayList;

import be.coworkers.quizzakko.data.model.dao.Response;

/**
 *
 * Created by sebastienk on 24-Jan-16.
 */
public class OrderingQuestion extends Question  implements IQuestion{

    private ArrayList<Response> answers;

    public OrderingQuestion(){ }

    public OrderingQuestion(String question){
        setQuestion(question);
    }

    public OrderingQuestion(String question, ArrayList<Response> answers) {
        setQuestion(question);
        setAnswer(answers);
    }

    public ArrayList<Response> getAnswer() {
        return answers;
    }

    public void setAnswer(ArrayList<Response> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return super.toString();
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
        ///dest.writeStringList(this.answers);
        if (this.answers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.answers);
        }
    }

    protected OrderingQuestion(Parcel in) {
        super(in);
        if (in.readByte() == 0x01) {
            this.answers = new ArrayList<>();
            in.readList(this.answers, Question.class.getClassLoader());
        } else {
            this.answers = null;
        }
    }

    public static final Creator<OrderingQuestion> CREATOR = new Creator<OrderingQuestion>() {
        public OrderingQuestion createFromParcel(Parcel source) {
            return new OrderingQuestion(source);
        }

        public OrderingQuestion[] newArray(int size) {
            return new OrderingQuestion[size];
        }
    };
}
