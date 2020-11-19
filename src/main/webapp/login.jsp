<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <title>Login</title>
    <style>
        body{
            font-family: Arial;
        }
    </style>
</head>
<body>
    <%String err = request.getParameter("errorMsg");%>
    <div align="center">
        <br><br><br><br>
    <div style="background-color: lightgrey; width: 50%; border: solid black 1px; padding-bottom: 50px">
    <h1>Login to InstaBook</h1>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Name">
        <input type="password" name="password" placeholder="Password">
        <input type="submit" value="Login">
        <p style="color: #ff0000"><c:out value="${errorMsg}"/></p>
        <div class="g-recaptcha" data-sitekey="6LdrMeMZAAAAADZSX5WFK6fju3dLAwHK6jtpttBK"/>
    </form>
    </div>
    </div>
</body>
</html>