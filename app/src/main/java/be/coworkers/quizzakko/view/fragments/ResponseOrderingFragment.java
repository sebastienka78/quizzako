package be.coworkers.quizzakko.view.fragments;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.Response;
import be.coworkers.quizzakko.view.adapter.ListQuestionAdapter;

/**
 * ResponseOrderingFragment
 * Template for response of type ORDERING
 *
 * Created by sebastienk on 21-Feb-16.
 */
@EFragment(R.layout.fragment_ordering_response)
public class ResponseOrderingFragment extends Fragment implements ResponseFragment {

    ListQuestionAdapter responseAdapter = null;
    ArrayList<Response> responses = new ArrayList<>();

    @ViewById
    ListView listQuestionView;

    @AfterViews
    void initFragment() {

        responses.add(new Response(""));
        responses.add(new Response(""));
        responseAdapter = new ListQuestionAdapter(getContext(), R.layout.ordering_item, R.id.answerEditText, responses);
        listQuestionView.setAdapter(responseAdapter);
        responseAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                // addMarkers();
            }
        });
        responseAdapter.setNotifyOnChange(true);
    }

    @ItemClick(R.id.listQuestionView)
    void listItemClicked(Response response) {
        Toast.makeText(getContext(), "click: " + response.getAnswer(), Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.addResponseButton)
    void addResponseButtonClickEvent(View v) {
        Toast.makeText(getContext(), "click: " + String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getResponseText() {
        return null;
    }

    @Override
    public void setResponseText(String responseText) {

    }
}