<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>View Course - ${course.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            </aside>

        <main class="main-content">
            <header class="main-header">
                <h1>${course.title}</h1>
                <p>${course.introduction}</p>
            </header>

            <div class="course-content">
                <c:forEach var="lesson" items="${course.lessons}">
                    <div class="card">
                        <h3 class="card-title">${lesson.title}</h3>
                        <p class="card-description">${lesson.material}</p>
                    </div>
                </c:forEach>
            </div>
        </main>
    </div>
    <script>feather.replace();</script>
</body>
</html>