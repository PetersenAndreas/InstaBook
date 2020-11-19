<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create post</title>
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
        <h1>Create your post here</h1>
    <form action="/createpost" method="post" enctype = "multipart/form-data">
    <input type="text" name="title" placeholder="Post Title">
    <input type="file" name="file" size = "50">
    <br>
    <input type="submit" value="Create your Post">
</form>
</div>
</div>
</body>
</html>