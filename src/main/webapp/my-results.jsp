<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Results</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            <div class="sidebar-header"><h2>Quizzer</h2></div>
            <ul class="sidebar-nav">
                <li class="nav-item"><a href="dashboard"><i data-feather="grid"></i> Dashboard</a></li>
                <li class="nav-item active"><a href="my-results"><i data-feather="check-square"></i> My Results</a></li>
                <li class="nav-item"><a href="#"><i data-feather="user"></i> Profile</a></li>
            </ul>
            <div class="sidebar-footer"><a href="logout"><i data-feather="log-out"></i> Logout</a></div>
        </aside>

        <main class="main-content">
            <header class="main-header">
                <h1>My Results</h1>
                <p>A history of all the quizzes you have completed.</p>
            </header>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Quiz Subject</th>
                            <th>Score</th>
                            <th>Date Taken</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="result" items="${resultsList}">
                            <tr>
                                <td><c:out value="${result.quizSubject}" /></td>
                                <td><strong>${result.score} / ${result.totalQuestions}</strong></td>
                                <td>${result.formattedDate}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty resultsList}">
                            <tr>
                                <td colspan="3" class="text-center">You have not completed any quizzes yet.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
    <script>feather.replace();</script>
</body>
</html>