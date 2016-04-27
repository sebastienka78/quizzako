package be.coworkers.quizzakko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.data.preferences.helper.SharedPreferencesHelper;
import be.coworkers.quizzakko.data.sqlite.helper.DatabaseHelper;
import be.coworkers.quizzakko.view.AddQuizzActivity_;
import be.coworkers.quizzakko.view.ChallengeQuizzActivity_;
import be.coworkers.quizzakko.view.MyQuizzActivity_;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @SystemService WindowManager windowManager;

    @ViewById Button createQuizzButton;
    @ViewById Button myQuizzButton;
    @ViewById Button challengeQuizzButton;
    @ViewById Button settingsButton;

    /**
     * initialize MainActivity
     */
    @SuppressWarnings("unused") // it's actually used, just injected by Android annotation
    @AfterViews
    void init_main(){
        SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        sharedPreferencesHelper.initApplication(this);
    }

    /**
     * createQuizzButton click event handler
     */
    @Click({R.id.createQuizzButton})
    void gotoAddQuizzActivity() {
        Intent intent = new Intent(this, AddQuizzActivity_.class);
        startActivity(intent);
    }

    /**
     * myQuizzButton click event handler
     */
    @Click({R.id.myQuizzButton})
    void gotoMyQuizzActivity() {
        Intent intent = new Intent(this, MyQuizzActivity_.class);
        startActivity(intent);
    }

    /**
     * challengeQuizzButton click event handler
     */
    @Click({R.id.challengeQuizzButton})
    void exportDb() {
        //DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //databaseHelper.exportDB();
        //Toast.makeText(this, "DB exported", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChallengeQuizzActivity_.class);
        startActivity(intent);
    }

    /**
     * settingsButton click event handler
     */
    @Click({R.id.settingsButton})
    void deleteDb() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteDb();
        SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        sharedPreferencesHelper.initApplication(this);
        Toast.makeText(this, "DB deleted", Toast.LENGTH_SHORT).show();
    }
}
