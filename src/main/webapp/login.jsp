<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 09-11-2020
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login to InstaBook</h1>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Name">
        <input type="password" name="password" placeholder="Password">
        <input type="submit" value="Login">
    </form>
</body>
</html>
