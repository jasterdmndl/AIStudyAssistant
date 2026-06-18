package com.example.aistudyassistant.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.example.aistudyassistant.database.NotesRepository;
import com.example.aistudyassistant.database.FlashcardsRepository;
import com.example.aistudyassistant.database.StudyPlannerRepository;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aistudyassistant.R;

public class DashboardActivity extends AppCompatActivity {

    private Button btnNotes;
    private Button btnFlashcards;
    private Button btnPlanner;
    private TextView txtNotesCount;
    private TextView txtFlashcardsCount;
    private TextView txtTasksCount;
    private TextView txtCompletedCount;
    private ProgressBar progressStudy;
    private TextView txtProgressPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Button
        btnNotes = findViewById(R.id.btnNotes);
        btnFlashcards = findViewById(R.id.btnFlashcards);
        btnPlanner = findViewById(R.id.btnPlanner);

        // Stats
        txtNotesCount = findViewById(R.id.txtNotesCount);
        txtFlashcardsCount = findViewById(R.id.txtFlashcardsCount);
        txtTasksCount = findViewById(R.id.txtTasksCount);
        txtCompletedCount = findViewById(R.id.txtCompletedCount);

        // Progress Bar
        progressStudy = findViewById(R.id.progressStudy);
        txtProgressPercent = findViewById(R.id.txtProgressPercent);

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
                    StudyPlannerActivity.class
            ));
        });
    }
    @SuppressLint("SetTextI18n")
    private void loadStatistics() {

        NotesRepository notesRepository =
                new NotesRepository(this);

        FlashcardsRepository flashcardsRepository =
                new FlashcardsRepository(this);

        StudyPlannerRepository plannerRepository =
                new StudyPlannerRepository(this);

        txtNotesCount.setText(
                String.valueOf(
                        notesRepository
                                .getTotalNotesCount()
                )
        );

        txtFlashcardsCount.setText(
                String.valueOf(
                        flashcardsRepository
                                .getTotalFlashcardsCount()
                )
        );

        txtTasksCount.setText(
                String.valueOf(
                        plannerRepository
                                .getTotalTaskCount()
                )
        );

        txtCompletedCount.setText(
                String.valueOf(
                        plannerRepository
                                .getCompletedTaskCount()
                )
        );

        int totalTasks = plannerRepository.getTotalTaskCount();
        int completedTasks = plannerRepository.getCompletedTaskCount();
        int percentage = 0;
        if(totalTasks > 0) {

            percentage =
                    (completedTasks * 100)
                            / totalTasks;
        }
        progressStudy.setProgress(
                percentage
        );
        txtProgressPercent.setText(
                percentage + "% Completed"
        );
    }
    @Override
    protected void onResume() {
        super.onResume();

        loadStatistics();
    }
}