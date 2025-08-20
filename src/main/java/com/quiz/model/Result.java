package com.quiz.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Result {
    private String quizSubject;
    private int score;
    private int totalQuestions;
    private Timestamp dateTaken;

    // Getters and Setters
    public String getQuizSubject() { return quizSubject; }
    public void setQuizSubject(String quizSubject) { this.quizSubject = quizSubject; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }
    public Timestamp getDateTaken() { return dateTaken; }
    public void setDateTaken(Timestamp dateTaken) { this.dateTaken = dateTaken; }

    // Helper method to format the date nicely for the JSP page
    public String getFormattedDate() {
        if (dateTaken == null) return "";
        return new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(dateTaken);
    }
}