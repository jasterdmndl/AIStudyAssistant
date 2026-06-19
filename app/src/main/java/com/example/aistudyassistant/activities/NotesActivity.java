package com.example.aistudyassistant.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.aistudyassistant.R;
import com.example.aistudyassistant.adapters.NotesAdapter;
import com.example.aistudyassistant.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.aistudyassistant.database.NotesRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private static final int ADD_NOTE_REQUEST = 1;
    private TextInputEditText etSearch;

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

    private void openEditNote(Note note) {

        Intent intent =
                new Intent(
                        this,
                        AddNoteActivity.class
                );

        intent.putExtra(
                "id",
                note.getId()
        );

        intent.putExtra(
                "title",
                note.getTitle()
        );

        intent.putExtra(
                "content",
                note.getContent()
        );

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setupBottomNavigation();
        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        nav.setSelectedItemId(R.id.nav_notes);
        RecyclerView recyclerNotes = findViewById(R.id.recyclerNotes);

        recyclerNotes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        notes = new ArrayList<>();

        NotesRepository repository =
                new NotesRepository(this);

        notes = repository.getAllNotes();

        etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(
                new android.text.TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        searchNotes(
                                s.toString()
                        );
                    }

                    @Override
                    public void afterTextChanged(
                            android.text.Editable s) {
                    }
                }
        );


        adapter = new NotesAdapter(
                notes,
                this::openEditNote,
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

    private void searchNotes(String keyword) {

        NotesRepository repository =
                new NotesRepository(this);

        notes.clear();

        if (keyword.isEmpty()) {

            notes.addAll(
                    repository.getAllNotes()
            );

        } else {

            notes.addAll(
                    repository.searchNotes(
                            keyword
                    )
            );
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadNotes();
    }

    // NAVIGATION BAR
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

                startActivity(
                        new Intent(
                                this,
                                StudyPlannerActivity.class
                        )
                );

                overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                );

                finish();

                return true;
            }

            return false;
        });
    }

}