package com.example.arunnagarajan.diplomate.Models;

public class Task {
    public Task(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void markTaskAsDone(){
        this.status = true;
    }
    String name, description, date;
    Boolean status = false;

    public String getName() {
        return name;
    }

    public Task() {
    }

    public void setName(String name) {

        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getFormattedDate() {
        return date.split("-")[0];
    }

    public void setDate(String date) {
        this.date = date;
    }
}
