package be.coworkers.quizzakko.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.Response;
import be.coworkers.quizzakko.data.model.dao.question.Question;

/**
 * be.coworkers.quizzakko.view.adapter
 * <p/>
 * Created by sebastienk on 29-Feb-16.
 */
public class ListQuestionAdapter extends ArrayAdapter<Response> {

    private Context context = null;
    public ArrayList<Response> original;

    /* here we must override the constructor for ArrayAdapter
     * the only variable we care about now is ArrayList<Item> objects,
     * because it is the list of objects we want to display.
     */
    public ListQuestionAdapter(Context context, int resource, int textViewResourceId, ArrayList<Response> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.original = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = null;
        if (convertView == null) {
            rowView = inflater.inflate(R.layout.ordering_item, parent, false);
        } else {
            rowView = convertView;
        }
        TextView answerNumberTextView = (TextView) rowView.findViewById(R.id.answerNumberTextView);
        EditText answerEditText = (EditText) rowView.findViewById(R.id.answerEditText);
        answerNumberTextView.setText(String.valueOf(position));
        return rowView;
    }
}
