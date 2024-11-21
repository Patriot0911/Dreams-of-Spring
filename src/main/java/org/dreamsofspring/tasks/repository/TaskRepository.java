package org.dreamsofspring.tasks.repository;

import org.dreamsofspring.tasks.entity.Task;
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
        this.tasks.add(new Task(UUID.randomUUID().toString(), "Watch GoT", "Just spend all your free time", 1, LocalDate.now().minusMonths(1), false));

        this.tasks.add(new Task(UUID.randomUUID().toString(), "Do homework", "Some math", 2, LocalDate.now(), false));

        this.tasks.add(new Task(UUID.randomUUID().toString(), "Go outside", "Touch some grass", 3, LocalDate.now().plusYears(4), true));
    }

    public List<Task> getAll() {
        return this.tasks;
    }

    public List<Task> getAllSorted(String sortBy, String order) {
        return this.tasks.stream()
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

        this.tasks.add(task);

        return task;
    }

    public Task getById(String id) {
        return this.tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task update(Task task) {
        return this.tasks.stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .map(existingTask -> {
                    tasks.set(tasks.indexOf(existingTask), task);
                    return task;
                })
                .orElse(null);
    }

    public Task deleteById(String id) {
        Task taskToDelete = this.getById(id);
        if (taskToDelete == null) return null;

        this.tasks.removeIf(task -> task.getId().equals(id));

        return taskToDelete;
    }
}
