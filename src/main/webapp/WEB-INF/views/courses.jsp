<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Courses - University Registration System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Available Courses</h1>
            <div class="user-info">
                Welcome, ${sessionScope.studentName} | 
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </div>
        </header>
        <c:if test="${not empty error}">
            <div class="error-message">
                <p>${error}</p>
            </div>
        </c:if>
        <table class="courses-table">
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Name</th>
                    <th>Instructor</th>
                    <th>Credits</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.name}</td>
                        <td>${course.instructor}</td>
                        <td>${course.credits}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/register/${course.courseId}" method="post">
                                <button type="submit">Register</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>