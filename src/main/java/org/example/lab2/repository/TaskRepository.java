package org.example.lab2.repository;

import org.example.lab2.entity.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<Task>();

    public TaskRepository() {
        tasks.add(new Task("lol", "tit", "desc", 5, LocalDate.now(), false));
    }

    public List<Task> getAll() {
        return tasks;
    }

    public void create(Task task) {
        tasks.add(task);
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
        Optional<Task> exTask = tasks.stream()
                .filter(task1 -> task1.getId().equals(task.getId()))
                .findFirst();

        if (exTask.isPresent()) {
            Task someTask = exTask.get();
            tasks.set(tasks.indexOf(someTask), task);
            return task;
        }

        return null;
    }
}
