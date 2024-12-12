package org.dreamsofspring.tasks.mapper;

import org.dreamsofspring.tasks.entity.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        int priority = rs.getInt("priority");
        Date raw_date = rs.getDate("task_date");
        Boolean completed = rs.getBoolean("completed");

        LocalDate date = LocalDate.ofInstant(
            new Date(raw_date.getTime()).toInstant(),
            ZoneId.systemDefault()
        );

        Task task = new Task(title, description, priority, date, completed);
        task.setId(String.valueOf(id));
        return task;
    };
};
