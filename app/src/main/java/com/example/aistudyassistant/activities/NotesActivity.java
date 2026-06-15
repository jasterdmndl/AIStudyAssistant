package com.example.aistudyassistant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.adapters.NotesAdapter;
import com.example.aistudyassistant.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerNotes;
    private static final int ADD_NOTE_REQUEST = 1;

    private List<Note> notes;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        recyclerNotes = findViewById(R.id.recyclerNotes);

        recyclerNotes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        notes = new ArrayList<>();

        notes.add(new Note(
                1,
                "Java OOP",
                "Encapsulation, Inheritance, Polymorphism"
        ));

        notes.add(new Note(
                2,
                "Database",
                "SQLite will be used for local storage"
        ));

        adapter = new NotesAdapter(notes);

        recyclerNotes.setAdapter(adapter);

        FloatingActionButton fabAddNote =
                findViewById(R.id.fabAddNote);

        fabAddNote.setOnClickListener(v -> {

            Intent intent = new Intent(
                    NotesActivity.this,
                    AddNoteActivity.class
            );

            startActivityForResult(
                    intent,
                    ADD_NOTE_REQUEST
            );

        });
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == ADD_NOTE_REQUEST
                && resultCode == RESULT_OK
                && data != null) {

            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");

            notes.add(
                    new Note(title, content)
            );

            adapter.notifyItemInserted(
                    notes.size() - 1
            );
        }
    }

}