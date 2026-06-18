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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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


}