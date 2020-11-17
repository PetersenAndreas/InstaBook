<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create post</title>
</head>
<body>
<form action="/createpost" method="post" enctype = "multipart/form-data">
    <input type="text" name="title" placeholder="Post Title">
    <input type="file" name="file" size = "50">
    <br>
    <input type="submit" value="Create your Post">
</form>
</body>
</html>