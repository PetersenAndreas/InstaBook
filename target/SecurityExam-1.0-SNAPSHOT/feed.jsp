<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- c:out is used for preventing xss, we also imported it with the taglib in the top -->
    <h1>This is your InstaBook feed, <c:out value="${username}"/></h1>
    <p>When we have implemented it</p>
</body>
</html>