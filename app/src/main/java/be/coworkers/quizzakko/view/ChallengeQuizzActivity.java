package be.coworkers.quizzakko.view;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.Arrays;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.Quizz;
import be.coworkers.quizzakko.data.service.QuizzService;
import be.coworkers.quizzakko.view.adapter.QuizzAdapter;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */
@EActivity(R.layout.activity_my_quizz)
public class ChallengeQuizzActivity extends AppCompatActivity {

    ArrayList<Quizz> quizzList = new ArrayList<>();

    QuizzAdapter quizzAdapter = null;

    @RestService QuizzService quizzService;

    @ViewById ListView quizzListView;

    @AfterViews
    void onInit() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        //DatabaseHelper databaseHelper = new DatabaseHelper(this);
        // quizzList = databaseHelper.getAllQuizzFromUser(0);
        //userList = databaseHelper.getAllUser();
        initQuizzList();
        getQuizz();
    }

    @Background
    void getQuizz() {
        Quizz[] quizz_list = quizzService.getQuizz();
        updateQuizz(quizz_list);
    }

    @UiThread
    void updateQuizz(Quizz[] quizzs) {
        ArrayList<Quizz> quizzz = new ArrayList<>(Arrays.asList(quizzs));
        quizzAdapter = new QuizzAdapter(this, R.layout.question_list_item, R.id.question_list_item_label, quizzList);
        quizzListView.setAdapter(quizzAdapter);
        quizzAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                // addMarkers();
            }
        });
        quizzAdapter.updateQuizzList(quizzz);
        quizzAdapter.setNotifyOnChange(true);
    }


    @ItemClick(R.id.quizzListView)
    void listItemClicked(Quizz quizz) {
        Toast.makeText(this, "click: " + quizz.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddQuizzActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("quizz", quizz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initQuizzList(){
        quizzAdapter = new QuizzAdapter(this, R.layout.question_list_item, R.id.question_list_item_label, quizzList);
        quizzListView.setAdapter(quizzAdapter);
        quizzAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                // addMarkers();
            }
        });
        quizzAdapter.setNotifyOnChange(true);
        quizzAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.buttonAdd:
                //registerQuizz();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
