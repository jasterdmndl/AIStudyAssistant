package com.example.aistudyassistant.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.database.StudyPlannerRepository;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {

    private TextInputEditText etTaskTitle;
    private Button btnSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTaskTitle = findViewById(R.id.etTaskTitle);
        btnSaveTask = findViewById(R.id.btnSaveTask);

        btnSaveTask.setOnClickListener(v -> {

            String taskTitle =
                    etTaskTitle.getText().toString().trim();

            if(taskTitle.isEmpty()) {
                return;
            }

            StudyPlannerRepository repository =
                    new StudyPlannerRepository(this);

            repository.insertTask(taskTitle);

            finish();
        });
    }
}