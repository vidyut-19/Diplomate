package com.example.arunnagarajan.diplomate.Models;

import java.util.List;

public class UserProfile {
    String name, email;
    List<String> subject;

    public UserProfile(String name, String email, List<String> subject) {
        this.name = name;
        this.email = email;
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
