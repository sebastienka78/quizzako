package be.coworkers.quizzakko.data.model.dao.question;

import android.support.v4.app.Fragment;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.User;
import be.coworkers.quizzakko.view.fragments.ResponseDateFragment;
import be.coworkers.quizzakko.view.fragments.ResponseDateFragment_;
import be.coworkers.quizzakko.view.fragments.ResponseListFragment;
import be.coworkers.quizzakko.view.fragments.ResponseListFragment_;
import be.coworkers.quizzakko.view.fragments.ResponseMultipleChoiceFragment;
import be.coworkers.quizzakko.view.fragments.ResponseMultipleChoiceFragment_;
import be.coworkers.quizzakko.view.fragments.ResponseNumericFragment;
import be.coworkers.quizzakko.view.fragments.ResponseNumericFragment_;
import be.coworkers.quizzakko.view.fragments.ResponseOrderingFragment;
import be.coworkers.quizzakko.view.fragments.ResponseOrderingFragment_;
import be.coworkers.quizzakko.view.fragments.ResponseTrueFalseFragment;
import be.coworkers.quizzakko.view.fragments.ResponseTrueFalseFragment_;
import be.coworkers.quizzakko.view.fragments.ResponseWordFragment;
import be.coworkers.quizzakko.view.fragments.ResponseWordFragment_;

/**
 *
 * Created by sebastienk on 21-Feb-16.
 */
public class QuestionFactory {

    private static QuestionFactory instance = null;

    private QuestionFactory() {
        // Exists only to defeat instantiation.
    }
    public static QuestionFactory getInstance() {
        if(instance == null) {
            instance = new QuestionFactory();
        }
        return instance;
    }

    public Fragment getFragmentQuestion(int type) {
        switch (type) {
            case Question.WORD_QUESTION_TYPE:           return new ResponseWordFragment_();
            case Question.NUMERIC_QUESTION_TYPE:        return new ResponseNumericFragment_();
            case Question.TRUEFALSE_QUESTION_TYPE:      return new ResponseTrueFalseFragment_();
            case Question.DATE_QUESTION_TYPE:           return new ResponseDateFragment_();
            case Question.LIST_QUESTION_TYPE:           return new ResponseListFragment_();
            case Question.ORDERING_QUESTION_TYPE:       return new ResponseOrderingFragment_();
            case Question.MULTIPLECHOICE_QUESTION_TYPE: return new ResponseMultipleChoiceFragment_();
            default:                                    return new ResponseWordFragment_();
        }
    }

    public Question getQuestionInstance(int type) {
        switch (type) {
            case Question.WORD_QUESTION_TYPE:           return new WordQuestion();
            case Question.NUMERIC_QUESTION_TYPE:        return new NumericQuestion();
            case Question.TRUEFALSE_QUESTION_TYPE:      return new TrueFalseQuestion();
            case Question.DATE_QUESTION_TYPE:           return new DateQuestion();
            case Question.LIST_QUESTION_TYPE:           return new ListQuestion();
            case Question.ORDERING_QUESTION_TYPE:       return new OrderingQuestion();
            case Question.MULTIPLECHOICE_QUESTION_TYPE: return new MultipleChoiceQuestion();
            default:                                    return new WordQuestion();
        }
    }
}