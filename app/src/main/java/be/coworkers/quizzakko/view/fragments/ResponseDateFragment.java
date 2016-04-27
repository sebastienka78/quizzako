package be.coworkers.quizzakko.view.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.question.Question;
import be.coworkers.quizzakko.events.DateSelectedEvent;

/**
 * ResponseDateFragment
 * Template for response of type DATE
 *
 * Created by sebastienk on 21-Feb-16.
 */
@EFragment(R.layout.fragment_date_response)
public class ResponseDateFragment extends Fragment implements ResponseFragment {

    private EventBus bus = EventBus.getDefault();

    @ViewById
    public EditText responseEditText;

    @AfterViews
    void afterViews() {
        bus.register(this);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            Question question = bundle.getParcelable("question");
            if (question != null){
                String responseText = question.getStringAnswer();
                setResponseText(responseText);
            }
        }
    }

    @FocusChange(R.id.responseEditText)
    void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @SuppressWarnings("unused") // it's actually used, just injected by EventBus
    @Subscribe
    public void onEvent(DateSelectedEvent event) {
        responseEditText.setText(event.date.toString());
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
