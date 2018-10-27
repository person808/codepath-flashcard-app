package com.example.kainalu.studycards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class NewCardActivity extends AppCompatActivity {

    private ImageView cancelButton;
    private ImageView saveButton;
    private EditText questionView;
    private EditText answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        cancelButton = findViewById(R.id.cancel_button);
        saveButton = findViewById(R.id.save_button);
        questionView = findViewById(R.id.question_view);
        answerView = findViewById(R.id.answer_view);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewCardActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFlashcard();
            }
        });
    }

    private void saveFlashcard() {
        Intent intent = new Intent(NewCardActivity.this, MainActivity.class);
        intent.putExtra("question", questionView.getText().toString());
        intent.putExtra("answer", answerView.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
