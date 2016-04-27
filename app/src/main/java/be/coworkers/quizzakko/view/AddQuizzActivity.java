package be.coworkers.quizzakko.view;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.Quizz;
import be.coworkers.quizzakko.data.model.dao.question.Question;
import be.coworkers.quizzakko.data.sqlite.helper.DatabaseHelper;
import be.coworkers.quizzakko.view.adapter.QuestionAdapter;

@EActivity(R.layout.activity_add_quizz)
@OptionsMenu(R.menu.main_menu)
public class AddQuizzActivity extends AppCompatActivity {

    static final int PICK_QUESTION_REQUEST = 1;

    public Quizz quizz = null;

    private QuestionAdapter questionAdapter = null;
    private DatabaseHelper databaseHelper = null;

    @ViewById ListView questionListView;
    @ViewById EditText titleQuizzEditText;
    @ViewById EditText themeQuizzEditText;
    @ViewById ImageButton addQuestionButton;

    @Extra("returnedQuestion") Question question;

    /**
     * initQuizz initialize AddQuizzActivity
     */
    @AfterViews
    void initQuizz() {
        databaseHelper = new DatabaseHelper(this);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            quizz = bundle.getParcelable("quizz");
            if (quizz != null) {
                titleQuizzEditText.setText(quizz.getTitle());
            } else {
                quizz = new Quizz();
            }
        }else
            quizz = new Quizz();
        initQuestionList();
    }

    /**
     * gotoAddQuestionActivity navigate to addQuestionActivity
     *
     */
    @Click({R.id.addQuestionButton})
    void gotoAddQuestionActivity() {
        Intent intent = new Intent(this, AddQuestionActivity_.class);
        quizz.setTitle(titleQuizzEditText.getText().toString());
        quizz.addThemesByString(themeQuizzEditText.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("quizzo", quizz);
        intent.putExtras(bundle);
        startActivityForResult(intent, PICK_QUESTION_REQUEST);
    }

    /**
     * listItemClicked method is triggered when user choose a question from the list
     * @param question Question selected question
     */
    @ItemClick(R.id.questionListView)
    void listItemClicked(Question question) {
        Toast.makeText(this, "click: " + question.getQuestion(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddQuestionActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("quizzo", quizz);
        bundle.putParcelable("question", question);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * initQuestionList method is triggered to initialize question list
     *
     */
    private void initQuestionList() {
        questionAdapter = new QuestionAdapter(this, R.layout.question_list_item, R.id.question_list_item_label, quizz.questions);
        questionListView.setAdapter(questionAdapter);
        questionAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                // addMarkers();
            }
        });
        questionAdapter.setNotifyOnChange(true);
    }

    /**
     * registerQuizz method is triggered to save quizz to database
     *
     */
    @SuppressWarnings("unused") // it's actually used, just injected by Android annotation
    @OptionsItem(R.id.buttonAdd)
    void registerQuizz() {
        Toast.makeText(this, "Register Quizz", Toast.LENGTH_LONG).show();
        databaseHelper.insertQuizz(quizz);
        Intent intent = new Intent(this,MyQuizzActivity_.class);
        startActivity(intent);
    }

    /**
     * homeSelected method is triggered when user press Home button
     *
     */
    @SuppressWarnings("unused") // it's actually used, just injected by Android annotation
    @OptionsItem
    void homeSelected() {
        onBackPressed();
    }

    /**
     * onQuestionTypeButtonClickEvent method is triggered when user press Question Type button
     *
     */
    @OnActivityResult(PICK_QUESTION_REQUEST)
    protected void onActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
                question = data.getExtras().getParcelable("returnedQuestion");
                quizz.questions.add(question);
                questionAdapter.notifyDataSetChanged();
        }
    }

    /**
     * onBackPressed method is triggered when user press Back button
     *
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


