package com.example.aistudyassistant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aistudyassistant.database.NotesRepository;
import com.example.aistudyassistant.R;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            NotesRepository repository =
                    new NotesRepository(this);

            repository.insertNote(
                    title,
                    content
            );

            finish();
        });
    }
}