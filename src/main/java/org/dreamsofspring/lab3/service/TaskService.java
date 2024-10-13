package org.dreamsofspring.lab3.service;

import org.dreamsofspring.lab3.repository.TaskRepository;
import org.dreamsofspring.lab3.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ApplicationContext applicationContext;

    public List<Task> getAllTasks() {
        return taskRepository.getAll();
    }

    public List<Task> getSortedTasks(String sortBy, String order) {
        return taskRepository.getAllSorted(sortBy, order);
    }

    public Task getTask(String taskId) {
        return taskRepository.getById(taskId);
    }

    public Task addTask(Task task) {
        return taskRepository.create(task);
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Task task) {
        return taskRepository.update(task);
    }

    public Task protoTask() {
        return applicationContext.getBean(Task.class);
    }
}
