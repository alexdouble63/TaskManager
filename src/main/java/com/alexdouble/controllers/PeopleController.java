package com.alexdouble.controllers;

import com.alexdouble.dao.TasksDAO;
import com.alexdouble.models.Person;
import com.alexdouble.dao.DaoPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PeopleController {
    private DaoPeople daoPeople;
    private TasksDAO tasksDAO;

    @Autowired
    public PeopleController(DaoPeople daoPeople, TasksDAO tasksDAO) {
        this.daoPeople = daoPeople;
        this.tasksDAO = tasksDAO;
    }

    @GetMapping("/people")
    public String showAll(Model model) {
        model.addAttribute("people",daoPeople.showAll());
        return "people/showAll";
    }

    @GetMapping ("/people/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
       model.addAttribute("person", daoPeople.getPerson(id));
       model.addAttribute("listTasks", daoPeople.listTaskWichDoPerson(id));
       return "people/showPerson";
    }

    @GetMapping("/people/new")
    public String NewPerson(@ModelAttribute("person") Person person){
       return "people/new";

    }

    @PostMapping("/people")
    public String saveNewPerson(@ModelAttribute("person") Person person){
        daoPeople.saveNewPerson(person);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable("id") int id){

       daoPeople.deletePerson(id);
       return "redirect:/people";
    }

    @GetMapping("/people/editPerson/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){

       model.addAttribute("person", daoPeople.getPerson(id));
       return "/people/editPerson";
    }
    @PatchMapping("/people/{id}")
    public String saveEditPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
       daoPeople.editPerson(id, person);
       return "redirect:/people";
    }
}
