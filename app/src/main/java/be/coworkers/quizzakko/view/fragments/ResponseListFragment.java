package be.coworkers.quizzakko.view.fragments;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.R;

/**
 * ResponseListFragment
 * Template for response of type LIST
 *
 * Created by sebastienk on 21-Feb-16.
 */
@EFragment(R.layout.fragment_list_response)
public class ResponseListFragment extends Fragment implements ResponseFragment {

    @ViewById
    ImageButton addResponseButton;

    @Override
    public String getResponseText(){
        return null;
    }

    @Override
    public void setResponseText(String responseText) {

    }
}
