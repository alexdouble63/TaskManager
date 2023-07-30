package com.alexdouble.dao;

import com.alexdouble.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoPeople {

    private static int NUMBER_PEOPLE = 0;
    private static List<Person> people = new ArrayList<>();


    static {
        people.add(new Person(++NUMBER_PEOPLE,"Tom"));
        people.add(new Person(++NUMBER_PEOPLE,"Kol"));
        people.add(new Person(++NUMBER_PEOPLE,"Bal"));

    }

    public List<Person> showAll(){
        return people;
    }

    public Person getPerson(int id){
        return people.stream().filter(p->p.getId()==id).findFirst().get();

    }


   // public void deletePerson(int id){
    public void deletePerson(int id){
        people.remove(people.stream().filter(p->p.getId()==id).findFirst().get());

    }

    public void saveNewPerson(Person person)
    {
        person.setId(++NUMBER_PEOPLE);
        people.add(person);
    }

    public void editPerson(int id, Person personEdited){
        Person personBeforEdited = people.stream().filter(p->p.getId()==id).findFirst().get();
        personBeforEdited.setName(personEdited.getName());
    }
}
