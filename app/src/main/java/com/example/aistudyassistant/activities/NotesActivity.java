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
import com.example.aistudyassistant.database.NotesRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerNotes;
    private static final int ADD_NOTE_REQUEST = 1;

    private List<Note> notes;
    private NotesAdapter adapter;

    private void loadNotes() {

        NotesRepository repository =
                new NotesRepository(this);

        notes.clear();

        notes.addAll(
                repository.getAllNotes()
        );

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        recyclerNotes = findViewById(R.id.recyclerNotes);

        recyclerNotes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        notes = new ArrayList<>();

        NotesRepository repository =
                new NotesRepository(this);

        notes = repository.getAllNotes();

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
    protected void onResume() {
        super.onResume();

        loadNotes();
    }

}