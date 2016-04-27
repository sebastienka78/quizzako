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
import java.util.List;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.Quizz;
import be.coworkers.quizzakko.data.model.dao.User;
import be.coworkers.quizzakko.data.service.QuizzService;
import be.coworkers.quizzakko.data.sqlite.helper.DatabaseHelper;
import be.coworkers.quizzakko.view.adapter.QuizzAdapter;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */
@EActivity(R.layout.activity_my_quizz)
public class MyQuizzActivity extends AppCompatActivity {

    ArrayList<Quizz> quizzList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    QuizzAdapter quizzAdapter = null;


    @ViewById ListView quizzListView;

    @AfterViews
    void onInit() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        quizzList = databaseHelper.getAllQuizzFromUser(0);
        userList = databaseHelper.getAllUser();
        initQuizzList();
    }

    @Background
    void getQuizz() {
        //List<Quizz> quizzs = restClient.getQuizz();
        //updateQuizz(quizzs);
    }

    @UiThread
    void updateQuizz(ArrayList<Quizz> quizzs) {
        quizzAdapter.updateQuizzList(quizzs);
        quizzAdapter.setNotifyOnChange(true);
        //bookmarkList.startAnimation(fadeIn);
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
