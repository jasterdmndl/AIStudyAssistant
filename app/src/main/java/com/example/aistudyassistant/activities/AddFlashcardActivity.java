package com.example.aistudyassistant.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.database.FlashcardsRepository;
import com.google.android.material.textfield.TextInputEditText;

public class AddFlashcardActivity extends AppCompatActivity {

    private TextInputEditText etQuestion;
    private TextInputEditText etAnswer;
    private Button btnSaveFlashcard;
    private int flashcardId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        etQuestion = findViewById(R.id.etQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnSaveFlashcard = findViewById(R.id.btnSaveFlashcard);
        flashcardId =
                getIntent().getIntExtra(
                        "id",
                        -1
                );

        btnSaveFlashcard.setOnClickListener(v -> {

            String question =
                    etQuestion.getText().toString().trim();

            String answer =
                    etAnswer.getText().toString().trim();

            if(question.isEmpty() || answer.isEmpty()) {
                return;
            }

            FlashcardsRepository repository =
                    new FlashcardsRepository(this);

            if(flashcardId != -1) {

                etQuestion.setText(
                        getIntent().getStringExtra(
                                "question"
                        )
                );

                etAnswer.setText(
                        getIntent().getStringExtra(
                                "answer"
                        )
                );

                btnSaveFlashcard.setText(
                        "Update Flashcard"
                );
            }

            if(flashcardId == -1) {

                repository.insertFlashcard(
                        question,
                        answer
                );

            } else {

                repository.updateFlashcard(
                        flashcardId,
                        question,
                        answer
                );
            }

            finish();
        });
    }
}