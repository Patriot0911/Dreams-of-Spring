package org.dreamsofspring.tasks.repository;

import java.util.List;

import org.dreamsofspring.tasks.entity.Task;

public interface TaskDao {
    Task create(Task task);
    Task getById(String id);
    List<Task> getAll(int limit, int skip);
    List<Task> searchByTitle(String keyword);
    List<Task> getAllSorted(String sortBy, String order);
    List<Task> findCompleted(boolean completed);
    List<Task> findTasksByFilters(String title, String description, Integer priority, Boolean completed);
    List<Task> findWithCondition(int limit, int skip, String title, String description, Integer priority, Boolean completed, String sortBy, String order);
    Task update(Task task);
    String deleteById(String id);
};
