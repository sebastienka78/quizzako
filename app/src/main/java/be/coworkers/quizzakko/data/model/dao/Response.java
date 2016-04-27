package be.coworkers.quizzakko.data.model.dao;

/**
 * be.coworkers.quizzakko.data.model.dao
 * <p/>
 * Created by sebastienk on 29-Feb-16.
 */
public class Response {

    private String answer;
    private boolean isTrue;

    public String
    getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public Response(String answer, boolean isTrue) {
        this.answer = answer;
        this.isTrue = isTrue;
    }

    public Response(String answer) {
        this.answer = answer;
    }

}
