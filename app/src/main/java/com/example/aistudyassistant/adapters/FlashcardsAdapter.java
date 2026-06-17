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

    public FlashcardsAdapter(
            List<Flashcard> flashcards,
            OnFlashcardLongClickListener longClickListener) {

        this.flashcards = flashcards;
        this.longClickListener = longClickListener;
    }

    public interface OnFlashcardLongClickListener {
        void onFlashcardLongClick(Flashcard flashcard);
    }

    private final OnFlashcardLongClickListener longClickListener;

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
        holder.txtAnswer.setVisibility(
                holder.answerVisible
                        ? View.VISIBLE
                        : View.GONE
        );
        holder.itemView.setOnClickListener(v -> {

            holder.answerVisible =
                    !holder.answerVisible;

            holder.txtAnswer.setVisibility(
                    holder.answerVisible
                            ? View.VISIBLE
                            : View.GONE
            );

            holder.txtAnswerLabel.setVisibility(
                    holder.answerVisible
                            ? View.VISIBLE
                            : View.GONE
            );
        });

        holder.itemView.setOnLongClickListener(v -> {

            longClickListener.onFlashcardLongClick(
                    flashcard
            );

            return true;
        });

    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public static class FlashcardViewHolder
            extends RecyclerView.ViewHolder {
        boolean answerVisible = false;

        TextView txtQuestion;
        TextView txtAnswer;
        TextView txtAnswerLabel;

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
            txtAnswerLabel =
                    itemView.findViewById(
                            R.id.txtAnswerLabel
                    );
        }
    }
}