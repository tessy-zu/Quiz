package com.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Question;

public class QuizActivity extends AppCompatActivity {

    private Button TrueButton;
    private Button FalseButton;
    private Button NextButton;
    private TextView QuestionTextView;

    private Question[] Questions = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = Questions[index].getTextId();
        QuestionTextView.setText(question);

        TrueButton = (Button) findViewById(R.id.true_button);
        FalseButton = (Button) findViewById(R.id.false_button);
        TrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this,
                        Questions[index].answer() ? R.string.toast_correct : R.string.toast_incorrect,
                        Toast.LENGTH_SHORT).show();
            }
        });

        FalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT).show();
            }
        });

        NextButton = (Button) findViewById(R.id.next_button);
        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                index = (index + 1) % Questions.length;
                int question = Questions[index].getTextId();
                QuestionTextView.setText(question);
            }
        });
    }
}