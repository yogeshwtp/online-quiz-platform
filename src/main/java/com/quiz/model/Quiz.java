package com.quiz.model;

public class Quiz {
    private int id;
    private String subject;
    private String category; // Add this field

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Add these getter and setter methods for the category
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}