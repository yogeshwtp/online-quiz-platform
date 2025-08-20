<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz in Progress</title>
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
                <li class="nav-item"><a href="dashboard"><i data-feather="grid"></i> Dashboard</a></li>
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
                <h1>Java Basics Quiz</h1>
                <p>Answer all questions to the best of your ability.</p>
            </header>

            <div class="quiz-container">
                <form action="quiz" method="post">
                    <input type="hidden" name="quizId" value="${quizId}">
                    
                    <c:forEach var="question" items="${questions}" varStatus="loop">
                        <div class="question-block">
                            <p class="question-number">Question ${loop.count}</p>
                            <h3 class="question-text"><c:out value="${question.questionText}" /></h3>
                            
                            <div class="options-group">
                                <c:forEach var="option" items="${question.options}">
                                    <label class="option-label">
                                        <input type="radio" name="question_${question.id}" value="${option.id}" required>
                                        <span><c:out value="${option.optionText}" /></span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <button type="submit" class="btn">Submit Answers</button>
                </form>
            </div>
        </main>
    </div>

    <script>
      feather.replace();
    </script>
</body>
</html>