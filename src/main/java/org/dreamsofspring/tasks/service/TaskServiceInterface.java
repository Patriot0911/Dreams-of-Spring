package org.dreamsofspring.tasks.service;

import java.util.List;

import org.dreamsofspring.tasks.entity.Task;
import org.springframework.util.MultiValueMap;

public interface TaskServiceInterface {
    Task getTask(String taskId);
    Task addTask(Task task);
    Task updateTask(Task task);
    String deleteTask(String taskId);

    List<Task> getAllTasks();
    List<Task> findCompletedTasks(boolean completed);
    List<Task> getSortedTasks(String sortBy, String order);
    List<Task> getTasks(int pageSize, int pageNumber, MultiValueMap<String, String> params);
};
