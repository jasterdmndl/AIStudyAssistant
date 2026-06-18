package com.example.aistudyassistant.models;

public class StudyTask {

    private int id;
    private String title;
    private boolean completed;

    public StudyTask(
            int id,
            String title,
            boolean completed) {

        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}