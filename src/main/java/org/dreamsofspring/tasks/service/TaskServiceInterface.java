package org.dreamsofspring.tasks.service;

import java.util.List;

import org.dreamsofspring.tasks.entity.Task;

public interface TaskServiceInterface {
    List<Task> getAllTasks(int pageSize, int pageNumber);
    List<Task> getSortedTasks(String sortBy, String order);
    Task getTask(String taskId);
    Task addTask(Task task);
    Task updateTask(Task task);
    String deleteTask(String taskId);
    List<Task> findCompletedTasks(boolean completed);
};
