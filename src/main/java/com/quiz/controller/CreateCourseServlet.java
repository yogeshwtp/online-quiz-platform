package com.quiz.controller;

import com.quiz.dao.CourseDao;
import com.quiz.model.*;
import com.quiz.service.GenAIService; // Or OpenAIService, depending on which you are using
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createCourse")
public class CreateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Make sure this matches the AI service you are using (GenAIService or OpenAIService)
    private GenAIService genAIService = new GenAIService(); 
    private CourseDao courseDao = new CourseDao();

    /**
     * Handles the form submission from create-course.jsp.
     * It calls the AI service, parses the response, saves the content to the database,
     * and redirects the user to a new page to view the course.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String topic = request.getParameter("topic");
        String level = request.getParameter("level");

        // 1. Call the AI service to generate the course and quiz
        String generatedJson = genAIService.generateCourseContent(topic, level);

        if (generatedJson != null && !generatedJson.isEmpty()) {
            
        	// 2. Parse the JSON and save everything to the database
        	System.out.println("Attempting to save course for user ID: " + currentUser.getId());
        	int newCourseId = parseAndSave(generatedJson, currentUser.getId());
            
            if (newCourseId > 0) {
                // 3. Redirect to a new servlet to view the course, passing the new course ID
                response.sendRedirect("viewCourse?id=" + newCourseId);
                return;
            }
        }
        
        // If something fails, redirect the user back to the dashboard
        response.sendRedirect("dashboard");
    }

    /**
     * A helper method to parse the JSON string from the AI into Java objects
     * and save them to the database using the CourseDao.
     * @param json The JSON string from the AI.
     * @param userId The ID of the user creating the course.
     * @return The ID of the newly created course, or 0 if it fails.
     */
    private int parseAndSave(String json, int userId) {
        try {
            JSONObject root = new JSONObject(json);
            
            // Create Course object
            Course course = new Course();
            course.setTitle(root.getString("courseTitle"));
            course.setIntroduction(root.getString("introduction"));

            // Create Lesson objects
            List<Lesson> lessons = new ArrayList<>();
            JSONArray lessonsJson = root.getJSONArray("lessons");
            for (int i = 0; i < lessonsJson.length(); i++) {
                JSONObject lessonJson = lessonsJson.getJSONObject(i);
                Lesson lesson = new Lesson();
                lesson.setTitle(lessonJson.getString("lessonTitle"));
                lesson.setMaterial(lessonJson.getString("material"));
                lessons.add(lesson);
            }

            // Create Quiz and Question objects
            Quiz quiz = new Quiz();
            quiz.setSubject(course.getTitle()); 

            List<Question> questions = new ArrayList<>();
            JSONArray quizJson = root.getJSONArray("quiz");
            for (int i = 0; i < quizJson.length(); i++) {
                JSONObject qJson = quizJson.getJSONObject(i);
                Question question = new Question();
                question.setQuestionText(qJson.getString("questionText"));
                
                List<Option> options = new ArrayList<>();
                JSONArray optsJson = qJson.getJSONArray("options");
                for (int j = 0; j < optsJson.length(); j++) {
                    JSONObject oJson = optsJson.getJSONObject(j);
                    Option option = new Option();
                    option.setOptionText(oJson.getString("optionText"));
                    option.setCorrect(oJson.getBoolean("isCorrect"));
                    options.add(option);
                }
                question.setOptions(options);
                questions.add(question);
            }

            // Save everything to the database in a single transaction
            return courseDao.saveGeneratedCourse(course, lessons, quiz, questions, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Return 0 to indicate failure
        }
    }
}