<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Online Quiz</title>
    <%-- Link to your new stylesheet --%>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Login</h2>
    
    <%-- Display an error message if the 'error' parameter is present in the URL --%>
    <% if ("1".equals(request.getParameter("error"))) { %>
        <p style="color:red;">Invalid username or password. Please try again.</p>
    <% } %>
    
    <form action="login" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
    <p>
        Don't have an account? <a href="register.jsp">Register here</a>
    </p>
</body>
</html>