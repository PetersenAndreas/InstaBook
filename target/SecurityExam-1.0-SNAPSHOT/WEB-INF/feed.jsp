<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feed</title>
</head>
<body>
    <!-- c:out is used for preventing xss, we also imported it with the taglib in the top -->
    <h1>This is your InstaBook feed, <c:out value="${username}"/></h1>

    <img alt="Image" src="${allPosts}" class="img-thumbnail">

    <a href="/createpost.jsp">Make a post</a>
</body>
</html>