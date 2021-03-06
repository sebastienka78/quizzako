package be.coworkers.quizzakko.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.question.Question;

/**
 * Created by sebastienk on 21-Feb-16.
 *
 * LunchAdapter Class Feed Lunch ListView
 *
 */
public class QuestionAdapter extends ArrayAdapter<Question> implements Filterable {

    private Context context = null;
    public ArrayList<Question> original;

    /* here we must override the constructor for ArrayAdapter
     * the only variable we care about now is ArrayList<Item> objects,
     * because it is the list of objects we want to display.
     */
    public QuestionAdapter(Context context, int resource, int textViewResourceId, ArrayList<Question> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.original = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.question_list_item, parent, false);
            holder = new ViewHolder();
            // Get component from
            holder.titleTextView = (TextView) convertView.findViewById(R.id.question_list_item_label);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        // Fill text component with data
        Question question = original.get(position);
        holder.titleTextView.setText(question.getQuestion());
        // return row view
        return convertView;
    }

    /**
     * Add a Restaurant to original ArrayList
     * @param item Restaurant
     */
    @Override
    public void add(Question item){
        original.add(item);
    }

    /**
     * Get Filter
     * @return Filter
     */
    /*
    @Override
    public Filter getFilter() {

        if (filter == null)
            filter = (Filter)new QuizzTypeFilter(this, typeToFilter);

        return filter;
    }*/

    /**
     * Class View Holder
     * Keep view's components reference
     */
    static class ViewHolder {
        //ImageView questionTypeImageView = null;
        TextView titleTextView = null;
    }
}

