<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Security Check --%>
<!DOCTYPE html>
<html>
<head>
    <title>Dash board</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="sidebar">
        <div class="sidebar-header">
            <h3>Quizzer</h3>
        </div>
        <ul class="sidebar-nav">
            <li class="active"><a href="#"><i data-feather="grid"></i> Dashboard</a></li>
            <li><a href="#"><i data-feather="message-square"></i> My Results</a></li>
            <li><a href="#"><i data-feather="user"></i> Profile</a></li>
        </ul>
        <div class="sidebar-footer">
            <a href="logout"><i data-feather="log-out"></i> Logout</a>
        </div>
    </div>

    <main class="main-content">
        <header class="main-header">
            <h2>Welcome, ${currentUser.username}!</h2>
        </header>

        <section class="quiz-list">
            <h3>Available Quizzes</h3>
            <div class="quiz-cards">
                <c:forEach var="quiz" items="${quizList}">
                    <div class="card">
                        <h4><c:out value="${quiz.subject}" /></h4>
                        <p>A set of questions to test your knowledge.</p>
                        <a href="quiz?quizId=${quiz.id}" class="btn">Start Quiz</a>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>
    
    <script>
      feather.replace(); // Activates the Feather Icons
    </script>
</body>
</html>