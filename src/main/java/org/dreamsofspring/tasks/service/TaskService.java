package org.dreamsofspring.tasks.service;

import org.dreamsofspring.tasks.repository.TaskRepository;
import org.dreamsofspring.tasks.entity.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {
    private final TaskRepository taskRepository;
    private final ApplicationContext applicationContext;

    public TaskService(TaskRepository taskRepository, ApplicationContext applicationContext) {
        this.taskRepository = taskRepository;
        this.applicationContext = applicationContext;
    }

    public List<Task> getTasks(int pageSize, int pageNumber, MultiValueMap<String, String> params) {
        int limit = pageSize == 0 ? 10 : pageSize;
        if(params != null) {
            String sortBy = params.getFirst("sortBy") != null ? params.getFirst("sortBy") : "id";
            String order = params.getFirst("order");

            String title = params.getFirst("title");
            String description = params.getFirst("description");
            Integer priority = params.containsKey("priority") ? Integer.parseInt(params.getFirst("priority")) : null;
            Boolean completed = params.containsKey("completed") ? Boolean.parseBoolean(params.getFirst("completed")) : null;
            return this.taskRepository.findWithCondition(limit, pageNumber*limit, title, description, priority, completed, sortBy, order);
        };
        return this.taskRepository.findWithCondition(limit, pageNumber*limit, null, null, null, null, "id", null);
    };

    public List<Task> getAllTasks() {
        return this.taskRepository.getAll(Integer.MAX_VALUE, 0);
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
