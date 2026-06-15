package com.example.aistudyassistant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aistudyassistant.R;

public class DashboardActivity extends AppCompatActivity {

    private Button btnNotes;
    private Button btnFlashcards;
    private Button btnPlanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnNotes = findViewById(R.id.btnNotes);
        btnFlashcards = findViewById(R.id.btnFlashcards);
        btnPlanner = findViewById(R.id.btnPlanner);

        btnNotes.setOnClickListener(v -> {
            startActivity(new Intent(
                    DashboardActivity.this,
                    NotesActivity.class
            ));
        });

        btnFlashcards.setOnClickListener(v -> {
            startActivity(new Intent(
                    DashboardActivity.this,
                    FlashcardsActivity.class
            ));
        });

        btnPlanner.setOnClickListener(v -> {
            startActivity(new Intent(
                    DashboardActivity.this,
                    PlannerActivity.class
            ));
        });
    }
}