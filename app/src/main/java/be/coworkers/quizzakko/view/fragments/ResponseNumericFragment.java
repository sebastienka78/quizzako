package be.coworkers.quizzakko.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.question.Question;

/**
 * ResponseNumericFragment
 * Template for response of type NUMERIC
 *
 * Created by sebastienk on 21-Feb-16.
 */
@EFragment(R.layout.fragment_numeric_response)
public class ResponseNumericFragment extends Fragment implements ResponseFragment {

    @ViewById
    public EditText responseEditText;

    @AfterViews
    void afterViews() {
        Bundle bundle = getArguments();
        if(bundle!=null) {
            Question question = bundle.getParcelable("question");
            if (question != null){
                String responseText = question.getStringAnswer();
                setResponseText(responseText);
            }
        }
    }

    @Override
    public String getResponseText(){
        return responseEditText.getText().toString();
    }

    @Override
    public void setResponseText(String responseText) {
        responseEditText.setText(responseText);
    }

}
