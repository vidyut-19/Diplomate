package com.example.arunnagarajan.diplomate.Models;

import java.lang.reflect.Constructor;
import java.util.List;

public class UserProfile {
    String name, email, examDate;
    List<String> subject;


    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public UserProfile(String name, String email, String examDate, List<String> subject) {
        this.name = name;
        this.email = email;
        this.examDate = examDate;
        this.subject = subject;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public UserProfile() {

    }
}
