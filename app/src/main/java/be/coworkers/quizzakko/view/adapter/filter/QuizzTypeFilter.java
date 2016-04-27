package be.coworkers.quizzakko.view.adapter.filter;

import android.widget.Filter;

import java.util.ArrayList;

import be.coworkers.quizzakko.data.model.dao.Quizz;
import be.coworkers.quizzakko.data.model.dao.question.Question;
import be.coworkers.quizzakko.view.adapter.QuizzAdapter;

/**
 *
 * Created by sebastienk on 24-Nov-15.
 */
public class QuizzTypeFilter extends Filter {

    private QuizzAdapter arrayAdapter = null;
    private ArrayList<Quizz> original = null;
    private ArrayList<Quizz> fitems = null;
    public String typeToFind;

    public QuizzTypeFilter(QuizzAdapter arrayAdapter, String typeToFind){
        this.arrayAdapter = arrayAdapter;
        this.original = new ArrayList<>(arrayAdapter.original);
        this.typeToFind = typeToFind;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length()> 0)
        {
            String prefix = constraint.toString().toLowerCase();
            final ArrayList<Quizz> list = new ArrayList<>(original);
            final ArrayList<Quizz> nlist = new ArrayList<>();
            int count = list.size();
            Quizz quizz = null;
            Question question = null;
            for (int i=0; i<count; i++) {
                quizz = list.get(i);
                question = quizz.questions.get(0);
                final int value = question.getType();
                /**if (value.equalsIgnoreCase(prefix)) {
                    nlist.add(quizz);
                }**/
            }
            results.values = nlist;
            results.count = nlist.size();
        }
        else
        {
            ArrayList<Quizz> list = new ArrayList<>(original);
            results.values = list;
            results.count = list.size();
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        if(results.values != null){
            fitems = (ArrayList<Quizz>)results.values;
            arrayAdapter.clear();
            int count = fitems.size();
            for (int i=0; i<count; i++)
            {
                Quizz quizz = fitems.get(i);
                arrayAdapter.add(quizz);
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
