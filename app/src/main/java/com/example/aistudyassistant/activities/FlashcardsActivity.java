package com.example.aistudyassistant.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.adapters.FlashcardsAdapter;
import com.example.aistudyassistant.database.FlashcardsRepository;
import com.example.aistudyassistant.models.Flashcard;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FlashcardsActivity extends AppCompatActivity {

    private RecyclerView recyclerFlashcards;
    private FloatingActionButton fabAddFlashcard;

    private List<Flashcard> flashcards;
    private FlashcardsAdapter adapter;

    private void showDeleteDialog(
            Flashcard flashcard) {

        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Delete Flashcard")
                .setMessage(
                        "Delete this flashcard?"
                )
                .setPositiveButton(
                        "Delete",
                        (dialog, which) -> {

                            FlashcardsRepository repository =
                                    new FlashcardsRepository(this);

                            repository.deleteFlashcard(
                                    flashcard.getId()
                            );

                            loadFlashcards();
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
        setContentView(R.layout.activity_flashcards);

        recyclerFlashcards =
                findViewById(R.id.recyclerFlashcards);

        fabAddFlashcard =
                findViewById(R.id.fabAddFlashcard);

        recyclerFlashcards.setLayoutManager(
                new LinearLayoutManager(this)
        );

        flashcards = new ArrayList<>();

        setupBottomNavigation();
        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        nav.setSelectedItemId(R.id.nav_flashcards);

        adapter =
                new FlashcardsAdapter(
                        flashcards,
                        this::openEditFlashcard,
                        this::showOptionsDialog
                );

        recyclerFlashcards.setAdapter(adapter);

        fabAddFlashcard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            FlashcardsActivity.this,
                            AddFlashcardActivity.class
                    );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadFlashcards();
    }

    private void loadFlashcards() {

        FlashcardsRepository repository =
                new FlashcardsRepository(this);

        flashcards.clear();

        flashcards.addAll(
                repository.getAllFlashcards()
        );

        adapter.notifyDataSetChanged();
    }

    private void showOptionsDialog(
            Flashcard flashcard) {

        String[] options = {
                "Edit Flashcard",
                "Delete Flashcard"
        };

        new AlertDialog.Builder(this)
                .setTitle("Flashcard Options")
                .setItems(
                        options,
                        (dialog, which) -> {

                            if(which == 0) {

                                openEditFlashcard(
                                        flashcard
                                );

                            } else {

                                showDeleteDialog(
                                        flashcard
                                );
                            }
                        }
                )
                .show();
    }

    private void openEditFlashcard(
            Flashcard flashcard) {

        Intent intent =
                new Intent(
                        this,
                        AddFlashcardActivity.class
                );

        intent.putExtra(
                "id",
                flashcard.getId()
        );

        intent.putExtra(
                "question",
                flashcard.getQuestion()
        );

        intent.putExtra(
                "answer",
                flashcard.getAnswer()
        );

        startActivity(intent);
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