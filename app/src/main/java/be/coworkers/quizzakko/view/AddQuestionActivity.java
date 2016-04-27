package be.coworkers.quizzakko.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import be.coworkers.quizzakko.R;
import be.coworkers.quizzakko.data.model.dao.Quizz;
import be.coworkers.quizzakko.data.model.dao.question.Question;
import be.coworkers.quizzakko.data.model.dao.question.QuestionFactory;
import be.coworkers.quizzakko.view.fragments.ResponseFragment;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */
@EActivity(R.layout.activity_add_question)
@OptionsMenu(R.menu.main_menu)
public class AddQuestionActivity extends AppCompatActivity {

    final private static int RESULT_LOAD_IMAGE = 1;
    private Fragment responseFragment = null;

    @ViewById TextView addQuestionQuizzTitle;
    @ViewById EditText questionTitleEditText;
    @ViewById EditText responseEditText;
    @ViewById Button questionTypeButton;
    @Extra("quizz") Quizz quizz;
    @Extra("question") Question question;

    @AfterViews
    void initQuestion() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        quizz = bundle.getParcelable("quizzo");
        question = bundle.getParcelable("question");
        addQuestionQuizzTitle.setText(quizz.getTitle());

        if(question != null){
            questionTitleEditText.setText(question.getQuestion());
            addFragmentquestion(question.getType(), true);
        }
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    /**
     * onQuestionTypeButtonClickEvent method is triggered when user press Question Type button
     *
     */
    @Click({R.id.questionTypeButton})
    void onQuestionTypeButtonClickEvent(View sender) {
        registerForContextMenu(sender);
        openContextMenu(sender);
        unregisterForContextMenu(sender);
    }

    /**
     * onQuestionAddImageButtonClickEvent method is triggered when user press Add Picture button
     *
     */
    @Click({R.id.questionAddImageButton})
    void onQuestionAddImageButtonClickEvent() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    /**
     * addFragmentquestion method is triggered when user choose a Question Type
     * Adding a new Fragment with corresponding Question Type UI
     *
     */
    private void addFragmentquestion(int type, boolean editMode) {
        QuestionFactory questionFactory = QuestionFactory.getInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(responseFragment == null) {
            responseFragment = questionFactory.getFragmentQuestion(type);
            fragmentTransaction.add(R.id.response_fragment, responseFragment, "HELLO");
        } else {
            responseFragment = questionFactory.getFragmentQuestion(type);
            fragmentTransaction.replace(R.id.response_fragment, responseFragment, "HELLO");
        }
        if(editMode) {
            Bundle data = new Bundle();
            data.putParcelable("question", question);
            responseFragment.setArguments(data);
        }
        // if(type == Question.DATE_QUESTION_TYPE)
        //responseFragment.showDatePickerDialog();
        fragmentTransaction.commit();
        fragmentTransaction.show(responseFragment);
    }

    /**
     * addQuestionToQuizz method is triggered when user press Add button
     *
     */
    @SuppressWarnings("unused") // it's actually used, just injected by Android annotation
    @OptionsItem(R.id.buttonAdd)
    void addQuestionToQuizz() {
        Toast.makeText(this, "Add Question to Quizz", Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        String title = questionTitleEditText.getText().toString();
        String response = ((ResponseFragment) responseFragment).getResponseText();
        question.setQuestion(title);
        question.setAnswerFromString(response);
        returnIntent.putExtra("returnedQuestion", question);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    /**
     * homeSelected method is triggered when user press home button
     *
     */
    @SuppressWarnings("unused") // it's actually used, just injected by Android annotation
    @OptionsItem
    void homeSelected() {
        onBackPressed();
    }

    /**
     * onActivityResult method is triggered when user choose a picture in the previous activity
     *
     */
    @SuppressWarnings("unused") // it's actually used, just injected by Android annotation
    @OnActivityResult(RESULT_LOAD_IMAGE)
    protected void onActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.close();
            }
            //cursor.moveToFirst();
            //int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //String picturePath = cursor.getString(columnIndex);
            //cursor.close();
        }
    }

    /**
     * onCreateContextMenu method is triggered when menu is created
     *
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choisir un type de question");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_type_menu, menu);
    }

    /**
     * onContextItemSelected method is triggered when user choose Question Type in the menu
     *
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        addFragmentquestion(item.getOrder(), false);
        QuestionFactory questionFactory = QuestionFactory.getInstance();
        question = questionFactory.getQuestionInstance(item.getOrder());
        question.setType(item.getOrder());
        return true;
    }

    /**
     * onBackPressed method is triggered when user press BackButton
     *
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


