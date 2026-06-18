package com.example.aistudyassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudyassistant.R;
import com.example.aistudyassistant.models.StudyTask;

import java.util.List;

public class StudyPlannerAdapter extends RecyclerView.Adapter<StudyPlannerAdapter.TaskViewHolder> {

    private final List<StudyTask> tasks;

    public StudyPlannerAdapter(List<StudyTask> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_task,
                                parent,
                                false
                        );

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TaskViewHolder holder,
            int position) {

        StudyTask task = tasks.get(position);

        holder.txtTaskTitle.setText(
                task.getTitle()
        );

        holder.checkCompleted.setChecked(
                task.isCompleted()
        );
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtTaskTitle;
        CheckBox checkCompleted;

        public TaskViewHolder(
                @NonNull View itemView) {

            super(itemView);

            txtTaskTitle =
                    itemView.findViewById(
                            R.id.txtTaskTitle
                    );

            checkCompleted =
                    itemView.findViewById(
                            R.id.checkCompleted
                    );
        }
    }
}