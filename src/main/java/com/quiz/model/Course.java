package com.quiz.model;

import java.util.List;

public class Course {
    private int id;
    private int quizId; // Make sure this line exists
    private String title;
    private String introduction;
    private List<Lesson> lessons; // Added to hold the lessons for a course

    // --- Getters and Setters ---
    public int getId() {
        return id;
    }
    public int getQuizId() {
        return quizId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}