package com.example.aistudyassistant.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

    private static final int ADD_NOTE_REQUEST = 1;

    private List<Note> notes;
    private NotesAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    private void loadNotes() {

        NotesRepository repository =
                new NotesRepository(this);

        notes.clear();

        notes.addAll(
                repository.getAllNotes()
        );

        adapter.notifyDataSetChanged();
    }

    private void showDeleteDialog(Note note) {

        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage(
                        "Are you sure you want to delete this note?"
                )
                .setPositiveButton(
                        "Delete",
                        (dialog, which) -> {

                            NotesRepository repository =
                                    new NotesRepository(this);

                            repository.deleteNote(
                                    note.getId()
                            );

                            loadNotes();
                            Toast.makeText(
                                    this,
                                    "Note deleted",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        null
                )
                .show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        RecyclerView recyclerNotes = findViewById(R.id.recyclerNotes);

        recyclerNotes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        notes = new ArrayList<>();

        NotesRepository repository =
                new NotesRepository(this);

        notes = repository.getAllNotes();

        adapter = new NotesAdapter(
                notes,
                this::showDeleteDialog
        );
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