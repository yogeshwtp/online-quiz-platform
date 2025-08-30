<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Results</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <h2>LEARN-ED</h2>
            </div>
            <ul class="sidebar-nav">
                <li class="nav-item"><a href="dashboard"><i data-feather="grid"></i> Dashboard</a></li>
				<!-- Change this line in all sidebars -->
				<li class="nav-item"><a href="create-course.jsp"><i data-feather="plus-circle"></i> Create Course</a></li>
				<li class="nav-item"><a href="my-results"><i data-feather="check-square"></i> My Results</a></li>
				<li class="nav-item"><a href="profile"><i data-feather="user"></i> Profile</a></li>            </ul>
            <div class="sidebar-footer">
                <a href="logout"><i data-feather="log-out"></i> Logout</a>
            </div>
        </aside>

        <main class="main-content">
            <div class="results-container">
                <div class="results-card">
                    <div class="results-icon">
                        <i data-feather="award"></i>
                    </div>
                    <h2 class="results-header">Quiz Completed!</h2>
                    <p class="results-subheader">Here's how you did:</p>
                    <div class="score-display">
                        <span class="score-value">${score}</span>
                        <span class="score-divider">/</span>
                        <span class="score-total">${totalQuestions}</span>
                    </div>
                    <p class="results-message">Great job! Your results have been saved.</p>
                    <a href="dashboard" class="btn">Back to Dashboard</a>
                </div>
            </div>
        </main>
    </div>

    <script>
      feather.replace();
    </script>
</body>
</html>