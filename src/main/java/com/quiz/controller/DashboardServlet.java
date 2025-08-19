package com.quiz.controller;

import com.quiz.dao.QuizDao;
import com.quiz.model.Quiz;
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
        // 1. Get the list of quizzes from the DAO
        List<Quiz> quizList = quizDao.getAllQuizzes();
        
        // 2. Put that list into the request object to pass to the JSP
        request.setAttribute("quizList", quizList);
        
        // 3. Forward the request (with the data) to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}