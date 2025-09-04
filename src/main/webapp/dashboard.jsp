<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- This security check redirects any non-logged-in user to the login page --%>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - LEARN-ED</title>
    <%-- Links to your stylesheet and the icon library --%>
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
                <li class="nav-item active"><a href="dashboard"><i data-feather="grid"></i> Dashboard</a></li>
                <li class="nav-item"><a href="create-course.jsp"><i data-feather="plus-circle"></i> Create Course</a></li>
                <li class="nav-item"><a href="my-results"><i data-feather="check-square"></i> My Results</a></li>
                <li class="nav-item"><a href="profile"><i data-feather="user"></i> Profile</a></li>
            </ul>
            <div class="sidebar-footer">
                <a href="logout"><i data-feather="log-out"></i> Logout</a>
            </div>
        </aside>

<main class="main-content">
    <header class="main-header">
        <h1>Welcome, ${currentUser.username}! 👋</h1>
        <p>Select a quiz to test your knowledge or create a new course.</p>
    </header>

    <section class="dashboard-section">
        <h2 class="section-title">📘 My Courses</h2>
        <div class="card-container">
            <c:forEach var="course" items="${userCourses}">
                <div class="card course-card">
                    <div class="card-icon-container">
                        <i data-feather="book"></i>
                    </div>
                    <div class="card-content">
                        <h3 class="card-title"><c:out value="${course.title}" /></h3>
                        <p class="card-description">AI-generated course material and quiz.</p>
                        <span class="card-status">Status: Not Started</span>
                    </div>
                    <div class="card-actions">
                        <a href="viewCourse?id=${course.id}" class="btn">View Course →</a>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty userCourses}">
                <p>You haven't generated any courses yet.</p>
            </c:if>
        </div>
    </section>

    <section class="dashboard-section">
        <h2 class="section-title">📝 Placement Practice Quizzes</h2>
        <c:forEach var="categoryEntry" items="${quizzesByCategory}">
            <h3 class="category-subtitle">${categoryEntry.key}</h3>
            <div class="card-container">
                <c:forEach var="quiz" items="${categoryEntry.value}">
                    <div class="card quiz-card">
                        <div class="card-icon-container">
                            <i data-feather="file-text"></i>
                        </div>
                        <div class="card-content">
                            <h3 class="card-title"><c:out value="${quiz.subject}" /></h3>
                            <p class="card-description">A series of pre-made questions on this topic.</p>
                        </div>
                        <div class="card-actions">
                            <a href="quiz?quizId=${quiz.id}" class="btn btn-secondary">Start Quiz →</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </section>
</main>
    </div>

    <script>
      // This script activates the Feather Icons
      feather.replace();
    </script>
</body>
</html>