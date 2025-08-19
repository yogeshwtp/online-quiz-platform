package com.quiz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quiz.dao.UserDao;
import com.quiz.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = userDao.validateUser(username, password);
        
        if (user != null) {
            // If user is valid, create a session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user); // Store user object in session
            
            // Redirect to the dashboard
            response.sendRedirect("dashboard");
        } else {
            // If user is invalid, redirect back to login page with an error
            response.sendRedirect("login.jsp?error=1");
        }
    }
}