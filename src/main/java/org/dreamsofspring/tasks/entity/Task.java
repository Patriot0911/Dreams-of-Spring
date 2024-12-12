package org.dreamsofspring.tasks.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Task {
    private String id;
    private String title;
    private String description;
    private Integer priority;
    private LocalDate date;
    private Boolean completed;

    public Task(String title, String description, Integer priority, LocalDate date, Boolean completed) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.completed = completed;
    };
};
