<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create user</title>
</head>

<body>
    <h1>Create a new user</h1>

    <form action="/createuser" method="post">
        <input type="text" name="username" placeholder="Name">
        <br>
        <input type="password" name="password" placeholder="Password">
        <br>
        <input type="text" name="email" placeholder="Email">
        <br>

        <select name="gender" id="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>
        <br>

        <input type="date" name="age">
        <br>

        <input type="submit" value="Create">
    </form>
</body>
</html>