package com.example.kainalu.studycards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionView;
    private TextView answerView;
    private ImageView newCardButtonView;
    private ImageView nextButton;

    private FlashcardDatabase flashcardDatabase;
    private List<Flashcard> allFlashcards;
    private int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        questionView = findViewById(R.id.flashcard_question);
        answerView = findViewById(R.id.flashcard_answer);
        newCardButtonView = findViewById(R.id.new_card_button);
        nextButton = findViewById(R.id.next_button);

        // Display flashcard if one exists in the database
        if (allFlashcards != null && allFlashcards.size() > 0) {
            questionView.setText(allFlashcards.get(0).getQuestion());
            answerView.setText(allFlashcards.get(0).getAnswer());
        }

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                questionView.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                answerView.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                // make sure question is showing
                answerView.setVisibility(View.INVISIBLE);
                questionView.setVisibility(View.VISIBLE);
            }
    });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            flashcardDatabase.insertCard(new Flashcard(question, answer));
            questionView.setText(question);
            answerView.setText(answer);
        }
    }
}
