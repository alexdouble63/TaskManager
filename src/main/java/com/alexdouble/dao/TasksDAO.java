package com.alexdouble.dao;

import com.alexdouble.models.StatusTask;
import com.alexdouble.models.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TasksDAO {

    private static int NUMBER_TASK = 0;
    private static List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task(++NUMBER_TASK,"Read project", StatusTask.NOT_ASSIGND));
        tasks.add(new Task(++NUMBER_TASK,"write project", StatusTask.NOT_ASSIGND));
        tasks.add(new Task(++NUMBER_TASK,"Show project", StatusTask.NOT_ASSIGND));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int id){
        return tasks.stream().filter(p->p.getId()==id).findFirst().get();
    }

    public void delete(int id){
        tasks.remove(tasks.stream().filter(p->p.getId()==id).findFirst().get());
    }


    public void saveNewTask(Task task){
        task.setId(++NUMBER_TASK);
        tasks.add(task);
    }

    public void saveEditedTask(Task editedTask){
        Task taskBeforEdit = tasks.stream().filter(p->p.getId()==editedTask.getId()).findFirst().get();
        taskBeforEdit.setDescription(editedTask.getDescription());
        taskBeforEdit.setStatusTask(editedTask.getStatusTask());
    }


}
