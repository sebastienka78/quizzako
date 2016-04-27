package be.coworkers.quizzakko.data.model.dao.question;

/**
 *
 * Created by sebastienk on 24-Feb-16.
 */
public interface IQuestion {

    void setAnswerFromString(String answerValue);
    String getStringAnswer();
    String getQuestion();
    void setQuestion(String questionText);
}
