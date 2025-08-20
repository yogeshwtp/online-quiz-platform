<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <h2>Quizzer</h2>
            </div>
            <ul class="sidebar-nav">
                <li class="nav-item active"><a href="#"><i data-feather="grid"></i> Dashboard</a></li>
                <!-- Change this line in all sidebars -->
				<li class="nav-item"><a href="my-results"><i data-feather="check-square"></i> My Results</a></li>
                <li class="nav-item"><a href="#"><i data-feather="user"></i> Profile</a></li>
            </ul>
            <div class="sidebar-footer">
                <a href="logout"><i data-feather="log-out"></i> Logout</a>
            </div>
        </aside>

        <main class="main-content">
            <header class="main-header">
                <h1>Welcome, ${currentUser.username}!</h1>
                <p>Select a quiz to test your knowledge.</p>
            </header>

            <section class="quiz-list">
                <div class="quiz-cards-container">
                    <c:forEach var="quiz" items="${quizList}">
                        <div class="card">
                            <h3 class="card-title"><c:out value="${quiz.subject}" /></h3>
                            <p class="card-description">A series of questions on this topic.</p>
                            <a href="quiz?quizId=${quiz.id}" class="btn">Start Quiz</a>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </main>
    </div>

    <script>
      feather.replace(); // This activates the icons
    </script>
</body>
</html>