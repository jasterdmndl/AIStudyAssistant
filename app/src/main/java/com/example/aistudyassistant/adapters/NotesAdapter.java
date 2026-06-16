package com.example.aistudyassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.models.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public interface OnNoteLongClickListener {
        void onNoteLongClick(Note note);
    }

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }
    private final OnNoteClickListener clickListener;
    private final OnNoteLongClickListener listener;

    private List<Note> notes;

    public NotesAdapter(
            List<Note> notes,
            OnNoteClickListener clickListener,
            OnNoteLongClickListener longClickListener) {

        this.notes = notes;
        this.clickListener = clickListener;
        this.listener = longClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note note = notes.get(position);

        holder.txtTitle.setText(note.getTitle());
        holder.txtContent.setText(note.getContent());
        holder.itemView.setOnClickListener(v -> {
            clickListener.onNoteClick(note);
        });

        holder.itemView.setOnLongClickListener(v -> {

            listener.onNoteLongClick(note);

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtContent;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
        }
    }
}