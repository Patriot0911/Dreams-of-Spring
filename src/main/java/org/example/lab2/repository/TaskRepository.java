package org.example.lab2.repository;

import org.example.lab2.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        String uuid = UUID.randomUUID().toString();
        tasks.add(new Task(uuid, "Do homework", "Some math", 2, LocalDate.now(), false));
    }

    public List<Task> getAll() {
        return tasks;
    }

    public List<Task> getAllSorted(String sortBy, String order) {
        return tasks.stream()
                .sorted((task1, task2) -> {
                    int comparison = 0;
                    if ("date".equalsIgnoreCase(sortBy)) {
                        comparison = task1.getDate().compareTo(task2.getDate());
                    } else if ("priority".equalsIgnoreCase(sortBy)) {
                        comparison = Integer.compare(task1.getPriority(), task2.getPriority());
                    }
                    return "desc".equalsIgnoreCase(order) ? -comparison : comparison;
                })
                .collect(Collectors.toList());
    }

    public Task create(Task task) {
        String uuid = UUID.randomUUID().toString();
        task.setId(uuid);
        tasks.add(task);
        return task;
    }

    public void deleteById(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public Task getById(String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task update(Task task) {
        return tasks.stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .map(existingTask -> {
                    tasks.set(tasks.indexOf(existingTask), task);
                    return task;
                })
                .orElse(null);
    }
}
