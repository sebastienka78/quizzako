package be.coworkers.quizzakko.data.model.dao.question;

import android.os.Parcel;

import java.util.ArrayList;

/**
 *
 * Created by sebastienk on 11-Feb-16.
 */
public class ListQuestion extends Question  implements IQuestion{

    private ArrayList<String> answer = new ArrayList<>();

    public ListQuestion(){ }

    public ListQuestion(String question){
        setQuestion(question);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
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
        dest.writeStringList(this.answer);
    }

    protected ListQuestion(Parcel in) {
        super(in);
        this.answer = in.createStringArrayList();
    }

    public static final Creator<ListQuestion> CREATOR = new Creator<ListQuestion>() {
        public ListQuestion createFromParcel(Parcel source) {
            return new ListQuestion(source);
        }

        public ListQuestion[] newArray(int size) {
            return new ListQuestion[size];
        }
    };
}
