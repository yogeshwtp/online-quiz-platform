<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quiz in Progress</title>
    <%-- The external stylesheet link can be kept or removed for this test --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>

    <style>
        .results-summary {
            margin-bottom: 2rem;
            background-color: var(--primary);
            color: var(--text-dark);
            text-align: center;
            padding: 1.5rem;
            border-radius: 12px;
        }
        
        /* Base style for options after submission */
        .submitted .option-label {
            transition: opacity 0.3s ease, background-color 0.3s ease;
        }

        /* Fade out the neutral, unselected options */
        .submitted .option-label:not(.correct-answer):not(.incorrect-answer) {
            opacity: 0.5;
            background-color: #f8f9fa;
        }

        /* Style for the CORRECT answer */
        .submitted .option-label.correct-answer {
            background-color: #d4edda !important; /* Use !important to override other styles */
            border-color: #c3e6cb !important;
            font-weight: 700 !important;
            color: #155724 !important;
        }

        /* Style for the user's INCORRECT choice */
        .submitted .option-label.incorrect-answer {
            background-color: #f8d7da !important;
            border-color: #f5c6cb !important;
            color: #721c24 !important;
            text-decoration: line-through;
        }

        /* Icon positioning */
        .submitted .option-label span { flex-grow: 1; }

        .submitted .option-label.correct-answer::after,
        .submitted .option-label.incorrect-answer::after {
            font-size: 1.5rem;
            font-weight: bold;
            margin-left: 1rem;
        }

        .submitted .option-label.correct-answer::after { content: '✔'; color: #155724; }
        .submitted .option-label.incorrect-answer::after { content: '✖'; color: #721c24; }
    </style>
    </head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <h2>LEARN-ED</h2>
            </div>
            <ul class="sidebar-nav">
                <li class="nav-item active"><a href="#"><i data-feather="grid"></i> Dashboard</a></li>
                <!-- Change this line in all sidebars -->
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
                <h1>Java Basics Quiz</h1>
            </header>

            <c:if test="${submitted}">
                <div class="card results-summary">
                    <h3>Quiz Complete! Your Score: ${score} / ${totalQuestions}</h3>
                </div>
            </c:if>

            <div class="quiz-container">
                <%-- We add a 'submitted' class to the form after the quiz is graded --%>
                <form action="quiz" method="post" class="${submitted ? 'submitted' : ''}">
                    <input type="hidden" name="quizId" value="${quizId}">
                    
                    <c:forEach var="question" items="${questions}" varStatus="loop">
                        <div class="question-block">
                            <p class="question-number">Question ${loop.count}</p>
                            <h3 class="question-text"><c:out value="${question.questionText}" /></h3>
                            
                            <div class="options-group">
                                <c:forEach var="option" items="${question.options}">
                                    <%-- This is a cleaner way to determine the CSS class --%>
                                    <c:set var="optionClass" value=""/>
                                    <c:if test="${submitted}">
                                        <c:choose>
                                            <c:when test="${option.correct}">
                                                <c:set var="optionClass" value="correct-answer"/>
                                            </c:when>
                                            <c:when test="${userAnswers[question.id] == option.id}">
                                                <c:set var="optionClass" value="incorrect-answer"/>
                                            </c:when>
                                        </c:choose>
                                    </c:if>

                                    <label class="option-label ${optionClass}">
                                        <input type="radio" name="question_${question.id}" value="${option.id}" required 
                                            ${userAnswers[question.id] == option.id ? 'checked' : ''} 
                                            ${submitted ? 'disabled' : ''}>
                                        <span><c:out value="${option.optionText}" /></span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${!submitted}">
                        <button type="submit" class="btn">Submit Answers</button>
                    </c:if>
                    <c:if test="${submitted}">
                        <a href="dashboard" class="btn">Back to Dashboard</a>
                    </c:if>
                </form>
            </div>
        </main>
    </div>
    <script>feather.replace();</script>
</body>
</html>