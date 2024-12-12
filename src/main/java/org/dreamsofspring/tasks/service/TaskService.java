package org.dreamsofspring.tasks.service;

import org.dreamsofspring.tasks.repository.TaskRepository;
import org.dreamsofspring.tasks.entity.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {
    private final TaskRepository taskRepository;
    private final ApplicationContext applicationContext;

    public TaskService(TaskRepository taskRepository, ApplicationContext applicationContext) {
        this.taskRepository = taskRepository;
        this.applicationContext = applicationContext;
    }

    public List<Task> getAllTasks(int pageSize, int pageNumber) {
        int limit = pageSize == 0 ? 10 : pageSize;
        return this.taskRepository.getAll(limit, pageNumber);
    };

    public List<Task> getSortedTasks(String sortBy, String order) {
        return taskRepository.getAllSorted(sortBy, order);
    };

    public Task getTask(String taskId) {
        return taskRepository.getById(taskId);
    };

    public Task addTask(Task task) {
        return taskRepository.create(task);
    };

    public Task updateTask(Task task) {
        return taskRepository.update(task);
    };

    @Transactional
    public String deleteTask(String taskId) {
        return taskRepository.deleteById(taskId);
    };

    public List<Task> findCompletedTasks(boolean completed) {
        return taskRepository.findCompleted(completed);
    };

    public Task protoTask() {
        return applicationContext.getBean(Task.class);
    };
};
