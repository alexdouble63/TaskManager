package com.alexdouble.models;

import java.util.Date;

public class Task {
    private int id;
    private String description;
    private StatusTask statusTask;
    private Date startDate;
    private Date compliteDate;


    public Task() {

    }

    public Task(int id, String description, StatusTask statusTask) {
        this.id = id;
        this.description = description;
        this.statusTask = statusTask;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
