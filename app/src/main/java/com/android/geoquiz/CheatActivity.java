package com.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.android.geoquiz.cheatActivity.answerIsTrue";
    private static final String EXTRA_ANSWER_IS_SHOWN = "com.android.geoquiz.cheatActivity.answerIsShown";
    boolean answerIsTrue;

    private TextView answerTextView;
    private Button showAnswer;

    public static Intent newIntent(Context packageContext, boolean cheatAnswerIsTrue) {
    Intent i = new Intent(packageContext, CheatActivity.class);
    i.putExtra(EXTRA_ANSWER_IS_TRUE, cheatAnswerIsTrue);
    return i;
    }

    public static boolean answerWasShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    }

    private void setShownAnswerResult(boolean answerIsShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, answerIsShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerTextView = findViewById(R.id.answer_text_view);
        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        showAnswer = findViewById(R.id.show_answer_button);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerIsTrue) {
                    answerTextView.setText(R.string.button_true);
                } else {
                    answerTextView.setText(R.string.button_false);
                }
                setShownAnswerResult(true);
            }
        });
    }
}
