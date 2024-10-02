package org.example.lab2.entity;

import java.time.LocalDate;

public class Task {
    private String id;
    private String title;
    private String description;
    private int priority;
    private LocalDate date;
    private boolean completed;

    public Task(String id, String title, String description, int priority, LocalDate date, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
