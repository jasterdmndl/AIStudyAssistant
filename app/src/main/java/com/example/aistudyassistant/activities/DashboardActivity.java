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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.aistudyassistant.R;

public class DashboardActivity extends AppCompatActivity {

    private TextView txtNotesCount;
    private TextView txtFlashcardsCount;
    private TextView txtTasksCount;
    private TextView txtCompletedCount;
    private ProgressBar progressStudy;
    private TextView txtProgressPercent;
    private TextView txtInsight;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //Navbar
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.nav_home) {

                return true;

            } else if(id == R.id.nav_notes) {

                startActivity(
                        new Intent(
                                this,
                                NotesActivity.class
                        )
                );

                return true;

            } else if(id == R.id.nav_flashcards) {

                startActivity(
                        new Intent(
                                this,
                                FlashcardsActivity.class
                        )
                );

                return true;

            } else if(id == R.id.nav_planner) {

                startActivity(
                        new Intent(
                                this,
                                StudyPlannerActivity.class
                        )
                );

                return true;
            }

            return false;
        });

        // Stats
        txtNotesCount = findViewById(R.id.txtNotesCount);
        txtFlashcardsCount = findViewById(R.id.txtFlashcardsCount);
        txtTasksCount = findViewById(R.id.txtTasksCount);
        txtCompletedCount = findViewById(R.id.txtCompletedCount);

        // Progress Bar
        progressStudy = findViewById(R.id.progressStudy);
        txtProgressPercent = findViewById(R.id.txtProgressPercent);

        // Insights
        txtInsight = findViewById(R.id.txtInsight);


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

        int notesCount = notesRepository.getTotalNotesCount();
        int flashcardsCount = flashcardsRepository.getTotalFlashcardsCount();

        txtInsight.setText(
                generateInsight(
                        notesCount,
                        flashcardsCount,
                        totalTasks,
                        completedTasks,
                        percentage
                )
        );
    }
    private String generateInsight(
            int notes,
            int flashcards,
            int totalTasks,
            int completedTasks,
            int percentage) {

        if(notes == 0) {

            return "Create your first study note to start building your knowledge base.";
        }

        if(flashcards == 0) {

            return "You have notes but no flashcards. Create flashcards to improve retention.";
        }

        if(totalTasks == 0) {

            return "Add study tasks to organize your learning schedule.";
        }

        if(percentage >= 90) {

            return "Excellent work! You completed " +
                    percentage +
                    "% of your planned tasks.";

        } else if(percentage >= 70) {

            return "Great progress! You completed " +
                    completedTasks +
                    " out of " +
                    totalTasks +
                    " tasks.";

        } else if(percentage >= 50) {

            return "Good progress. Complete a few more tasks to reach 70%.";

        } else {

            return "You have many unfinished tasks. Focus on your highest priority task.";
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        loadStatistics();
    }
}