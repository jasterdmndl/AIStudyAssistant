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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        etQuestion = findViewById(R.id.etQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnSaveFlashcard = findViewById(R.id.btnSaveFlashcard);

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

            repository.insertFlashcard(
                    question,
                    answer
            );

            finish();
        });
    }
}