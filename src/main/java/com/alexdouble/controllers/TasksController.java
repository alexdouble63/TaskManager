package com.alexdouble.controllers;

import com.alexdouble.dao.DaoPeople;
import com.alexdouble.dao.TasksDAO;
import com.alexdouble.models.Person;
import com.alexdouble.models.StatusTask;
import com.alexdouble.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private TasksDAO tasksDAO;
    private DaoPeople daoPeople;
    private static List<StatusTask> listStatus = Arrays.asList(StatusTask.values());

    @Autowired
    public TasksController(TasksDAO tasksDAO, DaoPeople daoPeople)
    {
        this.tasksDAO = tasksDAO;
        this.daoPeople = daoPeople;
    }

    @GetMapping()
    public String showTasks(Model model){
        model.addAttribute("tasks",tasksDAO.getTasks());
        return "/tasks/showTasks";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){

        model.addAttribute("task", tasksDAO.getTask(id));
        Person personForView = tasksDAO.getPerson(id);
        if (personForView!=null) model.addAttribute("owner", personForView);
        else model.addAttribute("listPeople", daoPeople.showAll());
        return "/tasks/showTask";
    }

    @PatchMapping("/{id}/assign")
    public String assignTask(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        tasksDAO.assign(id, person.getId());
        return "redirect:/tasks/"+id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        tasksDAO.release(id);
        return "redirect:/tasks/"+id;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") int id){

        tasksDAO.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task")Task task, Model model){
       // List<StatusTask> listStatus = Arrays.asList(StatusTask.values());
        model.addAttribute("listStatus", listStatus);
        return "/tasks/new";
    }

    @PostMapping()
    public String saveNewTask(@ModelAttribute ("task") Task newTask){

        tasksDAO.saveNewTask(newTask);

        return "redirect:/tasks";
    }

    @GetMapping("/editTask/{id}")
    public String editTask(@PathVariable("id") int id, Model model){
        model.addAttribute("task", tasksDAO.getTask(id));
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("listPerson", daoPeople.showAll());
        return "/tasks/editTask";

    }

    @PostMapping("/{id}")
    public String saveEditTask(@ModelAttribute("task") Task editedTask){
        tasksDAO.saveEditedTask(editedTask);
        return "redirect:/tasks";
    }

}
