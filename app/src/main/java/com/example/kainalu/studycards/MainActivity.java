package com.example.kainalu.studycards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionView;
    private TextView answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = findViewById(R.id.flashcard_question);
        answerView = findViewById(R.id.flashcard_answer);

        questionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionView.setVisibility(View.INVISIBLE);
                answerView.setVisibility(View.VISIBLE);
            }
        });

        answerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerView.setVisibility(View.INVISIBLE);
                questionView.setVisibility(View.VISIBLE);
            }
        });
    }
}
