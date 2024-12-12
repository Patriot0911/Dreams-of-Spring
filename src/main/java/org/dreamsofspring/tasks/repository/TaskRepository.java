package org.dreamsofspring.tasks.repository;

import org.dreamsofspring.tasks.entity.Task;
import org.dreamsofspring.tasks.mapper.TaskRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository implements TaskDao {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    public List<Task> getAll(int limit, int skip) {
        String sql = """
            SELECT *
            FROM (
                SELECT t.*, ROWNUM AS rnum
                FROM tasks t
                WHERE ROWNUM <= ? + ?
            )
            WHERE rnum > ?
            ORDER BY task_date DESC
        """;
        return jdbcTemplate.query(sql, new TaskRowMapper(), limit, skip, skip);
    };

    public List<Task> searchByTitle(String keyword) {
        String sql = "SELECT * FROM tasks WHERE title LIKE ?";
        String searchPattern = "%" + keyword + "%";

        return jdbcTemplate.query(sql, new Object[]{searchPattern}, new TaskRowMapper());
    };

    public List<Task> getAllSorted(String sortBy, String order) {
        String column = switch (sortBy.toLowerCase()) {
            case "priority" -> "priority";
            case "date" -> "task_date";
            default -> "id";
        };
        String sortOrder = "desc".equalsIgnoreCase(order) ? "DESC" : "ASC";
        String sql = String.format("SELECT * FROM tasks ORDER BY %s %s", column, sortOrder);
        return jdbcTemplate.query(sql, new TaskRowMapper());
    };

    public List<Task> findCompleted(boolean completed) {
        String sql = "SELECT * FROM tasks WHERE completed = ? ORDER BY task_date DESC";
        return jdbcTemplate.query(sql, new TaskRowMapper(), completed ? 1 : 0);
    };

    public Task getById(String id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TaskRowMapper(), id);
    };

    public Task update(Task task) {
        String updateSql = "UPDATE tasks SET title = ?, description = ?, priority = ?, task_date = ?, completed = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(updateSql,
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                java.sql.Date.valueOf(task.getDate()),
                task.getCompleted(),
                task.getId()
        );
        if (rowsAffected == 0)
            throw new RuntimeException("Task update failed or task not found");
        String selectSql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.queryForObject(selectSql, new TaskRowMapper(), task.getId());
    };

    public Task create(Task task) {
        String sql = "INSERT INTO tasks (title, description, priority, task_date, completed) " +
            "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setInt(3, task.getPriority());
            ps.setDate(4, java.sql.Date.valueOf(task.getDate()));
            ps.setBoolean(5, task.getCompleted());
            return ps;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            task.setId(String.valueOf(generatedId.intValue()));
        };

        return task;
    };

    public String deleteById(String id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        int affected = jdbcTemplate.update(sql, id);
        return String.valueOf(affected);
    };
};
