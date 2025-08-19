<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Security Check
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz in Progress</title>
</head>
<body>
    <h2>Quiz Time!</h2>
    
    <form action="quiz" method="post">
        <%-- This hidden input sends the quizId back to the servlet on submission --%>
        <input type="hidden" name="quizId" value="${quizId}">
        
        <ol>
            <%-- Loop through each Question object passed from the QuizServlet --%>
            <c:forEach var="question" items="${questions}">
                <li>
                    <h4><c:out value="${question.questionText}" /></h4>
                    
                    <%-- Loop through each Option for the current question --%>
                    <c:forEach var="option" items="${question.options}">
                        <input type="radio" name="question_${question.id}" value="${option.id}" required>
                        <label><c:out value="${option.optionText}" /></label><br>
                    </c:forEach>
                </li>
            </c:forEach>
        </ol>
        
        <input type="submit" value="Submit Answers">
    </form>
</body>
</html>