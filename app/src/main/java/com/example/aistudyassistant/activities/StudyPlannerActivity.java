package com.example.aistudyassistant.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.adapters.StudyPlannerAdapter;
import com.example.aistudyassistant.database.StudyPlannerRepository;
import com.example.aistudyassistant.models.StudyTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class StudyPlannerActivity extends AppCompatActivity {

    private RecyclerView recyclerTasks;
    private FloatingActionButton fabAddTask;

    private List<StudyTask> tasks;
    private StudyPlannerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_planner);

        recyclerTasks =
                findViewById(R.id.recyclerTasks);

        fabAddTask =
                findViewById(R.id.fabAddTask);

        recyclerTasks.setLayoutManager(
                new LinearLayoutManager(this)
        );

        tasks = new ArrayList<>();

        setupBottomNavigation();
        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        nav.setSelectedItemId(R.id.nav_planner);

        adapter =
                new StudyPlannerAdapter(
                        tasks,
                        this::updateTaskCompletion
                );

        recyclerTasks.setAdapter(adapter);

        fabAddTask.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            this,
                            AddTaskActivity.class
                    );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadTasks();
    }

    private void loadTasks() {

        StudyPlannerRepository repository =
                new StudyPlannerRepository(this);

        tasks.clear();

        tasks.addAll(
                repository.getAllTasks()
        );

        adapter.notifyDataSetChanged();
    }

    private void updateTaskCompletion(
            StudyTask task,
            boolean completed) {

        StudyPlannerRepository repository =
                new StudyPlannerRepository(this);

        repository.updateTaskCompletion(
                task.getId(),
                completed
        );

        loadTasks();
    }

    private void setupBottomNavigation() {

        BottomNavigationView bottomNavigation =
                findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.nav_home) {

                startActivity(
                        new Intent(
                                this,
                                DashboardActivity.class
                        )
                );

                overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                );

                finish();

                return true;
            }

            if(id == R.id.nav_notes) {

                startActivity(
                        new Intent(
                                this,
                                NotesActivity.class
                        )
                );

                overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                );

                finish();

                return true;
            }

            if(id == R.id.nav_flashcards) {

                startActivity(
                        new Intent(
                                this,
                                FlashcardsActivity.class
                        )
                );

                overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                );

                finish();

                return true;
            }

            if(id == R.id.nav_planner) {

                return true;
            }

            return false;
        });
    }


}