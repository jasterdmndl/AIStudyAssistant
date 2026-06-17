package com.example.aistudyassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.models.Flashcard;

import java.util.List;

public class FlashcardsAdapter extends RecyclerView.Adapter<FlashcardsAdapter.FlashcardViewHolder> {

    private final List<Flashcard> flashcards;

    public FlashcardsAdapter(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    @NonNull
    @Override
    public FlashcardViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_flashcard,
                                parent,
                                false
                        );

        return new FlashcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull FlashcardViewHolder holder,
            int position) {

        Flashcard flashcard =
                flashcards.get(position);

        holder.txtQuestion.setText(
                flashcard.getQuestion()
        );

        holder.txtAnswer.setText(
                flashcard.getAnswer()
        );
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public static class FlashcardViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtQuestion;
        TextView txtAnswer;

        public FlashcardViewHolder(
                @NonNull View itemView) {

            super(itemView);

            txtQuestion =
                    itemView.findViewById(
                            R.id.txtQuestion
                    );

            txtAnswer =
                    itemView.findViewById(
                            R.id.txtAnswer
                    );
        }
    }
}