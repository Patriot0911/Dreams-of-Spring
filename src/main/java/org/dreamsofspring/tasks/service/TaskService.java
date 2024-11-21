package org.dreamsofspring.tasks.service;

import org.dreamsofspring.tasks.repository.TaskRepository;
import org.dreamsofspring.tasks.entity.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ApplicationContext applicationContext;

    public TaskService(TaskRepository taskRepository, ApplicationContext applicationContext) {
        this.taskRepository = taskRepository;
        this.applicationContext = applicationContext;
    }

    private <T> boolean existOrEquals(T valueToExist, T valueToEquals) {
        return valueToExist == null || valueToExist.equals(valueToEquals);
    }

    private boolean isTaskMatchFilter(Task task, Task filter) {
        if (filter == null) return true;

        if (!this.existOrEquals(filter.getId(), task.getId())) return false;
        else if (!this.existOrEquals(filter.getTitle(), task.getTitle())) return false;
        else if (!this.existOrEquals(filter.getDescription(), task.getDescription())) return false;
        else if (!this.existOrEquals(filter.getPriority(), task.getPriority())) return false;
        else if (!this.existOrEquals(filter.getCompleted(), task.getCompleted())) return false;

        return true;
    }

    public List<Task> getAllTasks(Task filteringTask, int pageSize, int pageNumber) {
        List<Task> tasks = this.taskRepository.getAll()
                .stream()
                .filter((task) -> this.isTaskMatchFilter(task, filteringTask))
                .collect(Collectors.toList());

        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, tasks.size());

        if (startIndex > tasks.size()) {
            return Collections.emptyList();
        }

        return tasks.subList(startIndex, endIndex);
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

    public Task updateTask(Task task) {
        return taskRepository.update(task);
    }

    public Task deleteTask(String taskId) {
        return taskRepository.deleteById(taskId);
    }

    public Task protoTask() {
        return applicationContext.getBean(Task.class);
    }
}
