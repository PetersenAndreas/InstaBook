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
<h1 align="center">This is your InstaBook feed, <c:out value="${username}"/></h1>

<form align="center" action="/feed" method="post">
    <input type="submit" value="create new post"/>
</form>

<ul align="center">
    <c:forEach items="${allPosts}" var="strings">
            <li><c:out value="${strings.title}" /><li>
            <br>
            <img src="<c:out value="${strings.picturePath}"/>" style="max-width: 225px; width: 100%; max-height: 300px; height: auto;"/>
    </c:forEach>
</ul>

<c:if test="${sessionScope.get('username') != null}">
    <p>DEN VIRKER!!!!!! \('o')/</p>
    <p>${sessionScope.get("username").getUsername()}</p>
</c:if>

<p>Udenfor c:if ${sessionScope.get("username").getUsername()}</p>
<form action="logout" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>