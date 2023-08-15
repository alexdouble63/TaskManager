package com.alexdouble.dao;

import com.alexdouble.models.Person;
import com.alexdouble.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoPeople {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoPeople(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public  List<Person> showAll(){
        return jdbcTemplate.query("SELECT * FROM people", new PeopleMapper());
    }

    public  Person getPerson(int id){
        return jdbcTemplate.query("SELECT * FROM people WHERE id_person=?", new Object[]{id}, new PeopleMapper()).
                stream().findFirst().orElse(null);
    }

    public  void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM people WHERE id_person=?",id);
    }

    public  void saveNewPerson(Person person)
    {
        jdbcTemplate.update("INSERT INTO people (name) VALUES (?)",person.getName());
    }

    public  void editPerson(int id, Person personEdited){
        jdbcTemplate.update("UPDATE people SET name=? WHERE id_person=?", personEdited.getName(), personEdited.getId());
    }

    public List<Task> listTaskWichDoPerson(int id_person){
        return  jdbcTemplate.query("Select * From tasks where id_person=?",
                new Object[]{id_person}, new TaskMapper());
    }
}
