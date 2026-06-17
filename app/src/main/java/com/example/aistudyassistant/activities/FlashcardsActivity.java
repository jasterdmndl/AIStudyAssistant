package com.example.aistudyassistant.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.adapters.FlashcardsAdapter;
import com.example.aistudyassistant.database.FlashcardsRepository;
import com.example.aistudyassistant.models.Flashcard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FlashcardsActivity extends AppCompatActivity {

    private RecyclerView recyclerFlashcards;
    private FloatingActionButton fabAddFlashcard;

    private List<Flashcard> flashcards;
    private FlashcardsAdapter adapter;

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

        adapter =
                new FlashcardsAdapter(flashcards);

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
}