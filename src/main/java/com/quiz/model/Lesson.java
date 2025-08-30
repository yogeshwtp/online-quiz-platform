package com.quiz.model;

public class Lesson {
    private int id;
    private int courseId;
    private String title;
    private String material;

    // --- Getters and Setters ---
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
}