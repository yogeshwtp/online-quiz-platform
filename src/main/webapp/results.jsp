<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Quiz Results</title>
</head>
<body>
    <h2>Quiz Completed!</h2>
    
    <h3>Your Score: ${score} out of ${totalQuestions}</h3>
    
    <p>Your result has been saved.</p>
    
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>