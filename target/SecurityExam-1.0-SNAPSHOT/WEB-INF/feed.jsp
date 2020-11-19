<%@ page import="appLayer.User" %>
<%@ page import="appLayer.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.DBPosts" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>InstaBook Feed</title>
</head>
<body>
<%
    if (session.getAttribute("username") !=null) {
        User u = (User) session.getAttribute("username");
    } else {
        // log noget her...
        // Nogen prøvede at komme ind på admin page.
        // response.sendRedirect("/index.jsp");
        response.setStatus(403);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
%>
<!-- c:out is used for preventing xss, we also imported it with the taglib in the top -->
<div align="center">
<div style="background-color: lightgrey; width: 50%; margin: -10px; border: solid black 1px;">
<br>
<h1>This is your InstaBook feed, <c:out value="${username}"/></h1>

<c:if test="${sessionScope.get('username') != null}">

    <form action="/feed" method="post">
        <input type="submit" value="create new post"/>
    </form>
    <form action="logout" method="post">
        <input type="submit" value="Logout" onclick="myFunc()">
    </form>

    <c:forEach items="${allPosts}" var="strings">
            <h3 style="text-align: center;"><c:out value="${strings.title}" /></h3>
            <img src="<c:out value="${strings.picturePath}"/>" style="max-width: 300px; width: 100%; max-height: 370px; height: auto;"/>
            <br>
                <!--<img src="/img/commentIcon.png" style="width: 40px; height: 40px">-->
            <br>
            <hr style="margin: auto; align: center">
    </c:forEach>
</c:if>

    </div>
</div>

<script>
    function myFunc() {
        alert("You have been logged out!");
    }
</script>

</body>
</html>