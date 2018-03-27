package com.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Question;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;
    private TextView questionTextView;
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean isCheater;

    private Question[] questions = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int index = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, index);
    }

    private void updateQuestion() {
        questionTextView = findViewById(R.id.question_text_view);
        int question = questions[index].getTextId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questions[index].answer();
        int messageId;

        if (isCheater) {
            messageId = R.string.judgement_toast;
        } else {

            if (userPressedTrue == answerIsTrue) {
                messageId = R.string.toast_correct;
            } else {
                messageId = R.string.toast_incorrect;
            }
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        cheatButton = findViewById(R.id.cheat_button);

        trueButton.setOnClickListener(view -> checkAnswer(true));

        falseButton.setOnClickListener(view -> checkAnswer(false));

        cheatButton.setOnClickListener(view ->  {
                boolean cheatAnswerIsTrue = questions[index].answer();
                Intent i = CheatActivity.newIntent(QuizActivity.this, cheatAnswerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
        });

        nextButton.setOnClickListener(view -> {
                index = (index + 1) % questions.length;
                updateQuestion();
        });
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        isCheater = false;
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            isCheater = CheatActivity.answerWasShown(data);
        }
    }
}