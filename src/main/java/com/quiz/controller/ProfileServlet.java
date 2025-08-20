package com.quiz.controller;

import com.quiz.dao.UserDao;
import com.quiz.model.Profile;
import com.quiz.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();

    // Handles viewing the profile page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Profile profile = userDao.getUserProfile(currentUser.getId());
        request.setAttribute("userProfile", profile);
        request.setAttribute("user", currentUser); // Pass the main user object too

        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }

    // Handles saving the updated profile
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String fullName = request.getParameter("fullName");
        String bio = request.getParameter("bio");

        Profile profile = new Profile();
        profile.setUserId(currentUser.getId());
        profile.setFullName(fullName);
        profile.setBio(bio);

        userDao.updateUserProfile(profile);

        response.sendRedirect("profile"); // Redirect back to the profile page
    }
}