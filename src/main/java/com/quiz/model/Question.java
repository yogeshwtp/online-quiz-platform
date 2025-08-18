package com.quiz.model;

import java.util.List;

public class Question {
    private int id;
    private int quizId;
    private String questionText;
    private List<Option> options;

    // Constructors, Getters, and Setters
    public Question() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }
}