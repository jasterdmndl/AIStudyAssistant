package com.example.aistudyassistant.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.adapters.NotesAdapter;
import com.example.aistudyassistant.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        recyclerNotes = findViewById(R.id.recyclerNotes);

        recyclerNotes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        List<Note> notes = new ArrayList<>();

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

        NotesAdapter adapter = new NotesAdapter(notes);

        recyclerNotes.setAdapter(adapter);
    }
}