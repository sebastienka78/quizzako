package be.coworkers.quizzakko.view.fragments;

import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.R;

/**
 * ResponseMultipleChoiceFragment
 * Template for response of type MULTIPLE_CHOICE
 *
 * Created by sebastienk on 21-Feb-16.
 */
@EFragment(R.layout.fragment_mchoice_response)
public class ResponseMultipleChoiceFragment extends Fragment implements ResponseFragment {

    @Override
    public String getResponseText(){
        return null;
    }

    @Override
    public void setResponseText(String responseText) {

    }
}
