<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success - University Registration System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Registration Successful</h1>
        <div class="success-message">
            <p>${message}</p>
        </div>
        <div class="navigation-links">
            <a href="${pageContext.request.contextPath}/courses">Back to Courses List</a>
        </div>
    </div>
</body>
</html>