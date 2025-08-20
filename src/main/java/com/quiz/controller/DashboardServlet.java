package com.quiz.controller;

import com.quiz.dao.QuizDao;
import com.quiz.model.Quiz;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuizDao quizDao = new QuizDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the list of quizzes
        List<Quiz> quizList = quizDao.getAllQuizzes();

        // Group quizzes by category using Java Streams
        Map<String, List<Quiz>> quizzesByCategory = quizList.stream()
            .collect(Collectors.groupingBy(Quiz::getCategory));

        // Set the map as an attribute
        request.setAttribute("quizzesByCategory", quizzesByCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}