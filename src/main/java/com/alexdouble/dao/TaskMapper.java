package com.alexdouble.dao;

import com.alexdouble.models.StatusTask;
import com.alexdouble.models.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id_task"));
        task.setDescription(resultSet.getString("description"));
        task.setStatusTask(StatusTask.valueOf(resultSet.getString("statustask")));
        return task;
    }
}
