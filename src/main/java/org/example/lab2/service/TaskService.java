package org.example.lab2.service;

import org.example.lab2.entity.Task;
import org.example.lab2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.getAll();
    }

    public void addTask(Task task) {
        taskRepository.create(task);
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Task task) {
        return taskRepository.update(task);
    }

    @Bean
    @Scope("prototype")
    public Task protoTask() {
        return new Task(null, "Task", null, 0, null, false);
    }
}
