<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>

    <style>
        body{
            font-family: Arial;
        }
    </style>
</head>

<body>
    <div align="center">
        <br><br><br><br>
        <div style="background-color: lightgrey; width: 50%; border: solid black 1px; padding-bottom: 50px">
        <h1>Create a new user</h1>

    <form action="/createuser" method="post">
        <input type="text" name="username" placeholder="Name">
        <br>
        <input type="password" name="password" placeholder="Password">
        <br>
        <input type="text" name="email" placeholder="Email">
        <br>Gender:<br>
        <select name="gender" id="gender">
            <option value="">---SELECT---</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>
        <br>Birthday:<br>
        <input type="date" name="age">
        <br>
        <input type="submit" value="Create">
    </form>
    </div>
    </div>
</body>
</html>