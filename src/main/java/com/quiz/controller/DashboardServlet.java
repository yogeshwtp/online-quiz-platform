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
import javax.servlet.http.HttpSession; // For HttpSession
import com.quiz.dao.CourseDao;       // For CourseDao
import com.quiz.model.Course;         // For Course
import com.quiz.model.User;           // For User

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

 // In com.quiz.controller.DashboardServlet.java

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        // Security check
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // --- This is the main change ---
        // Fetch courses created ONLY by the current user
        CourseDao courseDao = new CourseDao();
        List<Course> userCourses = courseDao.getCoursesByUserId(currentUser.getId());

        // Pass the user-specific course list to the JSP
        request.setAttribute("userCourses", userCourses);

        // We will also keep the pre-made quizzes visible to everyone
        QuizDao quizDao = new QuizDao();
        List<Quiz> allQuizzes = quizDao.getAllQuizzes();
        Map<String, List<Quiz>> quizzesByCategory = allQuizzes.stream()
            .collect(Collectors.groupingBy(Quiz::getCategory));
        request.setAttribute("quizzesByCategory", quizzesByCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}