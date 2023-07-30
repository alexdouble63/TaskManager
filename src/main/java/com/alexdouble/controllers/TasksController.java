package com.alexdouble.controllers;

import com.alexdouble.dao.TasksDAO;
import com.alexdouble.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private TasksDAO tasksDAO;

    @Autowired
    public TasksController(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @GetMapping()
    public String showTasks(Model model){
        model.addAttribute("tasks",tasksDAO.getTasks());
        return "/tasks/showTasks";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") int id, Model model){
        model.addAttribute("task", tasksDAO.getTask(id));
        return "/tasks/showTask";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") int id){

        tasksDAO.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task")Task task){
        return "/tasks/new";
    }
}
