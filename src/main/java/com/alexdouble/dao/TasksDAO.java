package com.alexdouble.dao;

import com.alexdouble.models.Person;
import com.alexdouble.models.StatusTask;
import com.alexdouble.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TasksDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TasksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getTasks() {
        return  jdbcTemplate.query("select * from tasks", new TaskMapper());

    }

    public Task getTask(int id){
        return jdbcTemplate.query("select * from tasks where id_task=?", new Object[]{id},
                        new TaskMapper()).stream().findFirst().orElse(null);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from tasks where id_task=?",id);

    }


    public void saveNewTask(Task task){

        jdbcTemplate.update("insert into tasks (id_person,description,statusTask) values (?,?,?)",null,task.getDescription(),task.getStatusTask().name());
    }

    public void saveEditedTask(Task editedTask){
        jdbcTemplate.update("UPDATE tasks SET id_person=?, description=?, statusTask=? WHERE id_task=?", null,
            editedTask.getDescription(), editedTask.getStatusTask().name(),editedTask.getId());
    }

    public Person getPerson(int id){
        return jdbcTemplate.query("select people.* from tasks join people on tasks.id_person=people.id_person "+
                "where tasks.id_task=?", new Object[]{id}, new PeopleMapper()).stream().findFirst().orElse(null);
    }

    public void assign(int id, int id_person){
        jdbcTemplate.update("UPDATE tasks SET id_person=? WHERE id_task=?", id_person, id);
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE tasks SET id_person=? where id_task=?", null, id);
    }


}
