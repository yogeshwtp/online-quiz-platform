<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - LEARN-ED</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body class="auth-body">

    <div class="card auth-card">
        <h2 class="card-title text-center">Welcome Back!</h2>
        
        <%-- Display an error message if the login fails --%>
        <% if ("1".equals(request.getParameter("error"))) { %>
            <p class="error-message">Invalid username or password.</p>
        <% } %>

        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>

        <p class="auth-link">
            Don't have an account? <a href="register.jsp">Register here</a>
        </p>
    </div>

</body>
</html>