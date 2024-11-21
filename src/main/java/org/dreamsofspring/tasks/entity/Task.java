package org.dreamsofspring.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    private String id;
    private String title;
    private String description;
    private Integer priority;
    private LocalDate date;
    private Boolean completed;
}