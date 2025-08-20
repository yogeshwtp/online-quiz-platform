<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (session.getAttribute("currentUser") == null) { response.sendRedirect("login.jsp"); return; } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            <div class="sidebar-header"><h2>Quizzer</h2></div>
            <ul class="sidebar-nav">
                <li class="nav-item"><a href="dashboard"><i data-feather="grid"></i> Dashboard</a></li>
                <li class="nav-item"><a href="my-results"><i data-feather="check-square"></i> My Results</a></li>
                <li class="nav-item active"><a href="profile"><i data-feather="user"></i> Profile</a></li>
            </ul>
            <div class="sidebar-footer"><a href="logout"><i data-feather="log-out"></i> Logout</a></div>
        </aside>

        <main class="main-content">
            <header class="main-header">
                <h1>My Profile</h1>
                <p>View and update your personal information.</p>
            </header>

            <div class="card">
                <form action="profile" method="post">
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" class="form-control" value="${user.username}" disabled>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" value="${user.email}" disabled>
                    </div>
                    <hr style="margin: 2rem 0;">
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" id="fullName" name="fullName" class="form-control" value="${userProfile.fullName}">
                    </div>
                    <div class="form-group">
                        <label for="bio">Bio</label>
                        <textarea id="bio" name="bio" class="form-control" rows="4">${userProfile.bio}</textarea>
                    </div>
                    <button type="submit" class="btn">Save Changes</button>
                </form>
            </div>
        </main>
    </div>
    <script>feather.replace();</script>
</body>
</html>