package com.example.kainalu.studycards;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionView;
    private TextView answerView;
    private ImageView newCardButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = findViewById(R.id.flashcard_question);
        answerView = findViewById(R.id.flashcard_answer);
        newCardButtonView = findViewById(R.id.new_card_button);

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

        newCardButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            questionView.setText(question);
            answerView.setText(answer);
        }
    }
}
