package be.coworkers.quizzakko.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.question.Question;

/**
 * ResponseTrueFalseFragment
 * Template for response of type TRUEFALSE
 *
 * Created by sebastienk on 21-Feb-16.
 */
@EFragment(R.layout.fragment_truefalse_response)
public class ResponseTrueFalseFragment extends Fragment implements ResponseFragment {

    @ViewById RadioButton trueButton;
    @ViewById RadioButton falseButton;

    @AfterViews
    void afterViews() {
        Bundle bundle = getArguments();
       /* if(bundle!=null) {
            Question question = bundle.getParcelable("question");
            if (question != null){
                String responseText = question.getStringAnswer();
                if(responseText.equals("true")) {
                    trueButton.setChecked(true);
                    falseButton.setChecked(false);
                }else{
                    trueButton.setChecked(false);
                    falseButton.setChecked(true);
                }
            }
        }*/
    }

    @Override
    public String getResponseText(){
        return trueButton.isChecked() ? "true" : "false";
    }

    @Override
    public void setResponseText(String responseText) {
        if(responseText.equals("true")) {
            trueButton.setChecked(true);
            falseButton.setChecked(false);
        }else{
            trueButton.setChecked(false);
            falseButton.setChecked(true);
        }
    }
}
