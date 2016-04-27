package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

import java.util.ArrayList;

/**
 *
 * Created by sebastienk on 11-Feb-16.
 */
public class MultipleChoiceQuestion extends Question  implements IQuestion{

    private ArrayList<String> answers;

    public MultipleChoiceQuestion(){  }

    public MultipleChoiceQuestion(String question){
        setQuestion(question);
    }

    public MultipleChoiceQuestion(String question, ArrayList<String> answer){
        setQuestion(question);
        setAnswer(answer);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<String> getAnswer() {
        return answers;
    }

    public void setAnswer(ArrayList<String> answers) {
        this.answers = answers;
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
        dest.writeStringList(this.answers);
    }

    protected MultipleChoiceQuestion(Parcel in) {
        super(in);
        this.answers = in.createStringArrayList();
    }

    public static final Creator<MultipleChoiceQuestion> CREATOR = new Creator<MultipleChoiceQuestion>() {
        public MultipleChoiceQuestion createFromParcel(Parcel source) {
            return new MultipleChoiceQuestion(source);
        }

        public MultipleChoiceQuestion[] newArray(int size) {
            return new MultipleChoiceQuestion[size];
        }
    };
}
