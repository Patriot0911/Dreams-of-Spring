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
    Task update(Task task);
    String deleteById(String id);
};
