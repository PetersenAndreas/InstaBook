<%@ page import="appLayer.User" %>
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
        System.out.println(u.getUsername());
    } else {
        // log noget her...
        // Nogen prøvede at komme ind på admin page.
        // response.sendRedirect("/index.jsp");
        response.setStatus(403);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
%>
<!-- c:out is used for preventing xss, we also imported it with the taglib in the top -->
<h1>This is your InstaBook feed, <c:out value="${username}"/></h1>
<img alt="Image" src="${allPosts}">

<h1>Iterating over ArrayList</h1>
<ul>
    <c:forEach items="${allPosts}" var="value">
        <li><c:out value="${value}"/></li>
    </c:forEach>
</ul>

<a href="/WEB-INF/createpost.jsp">Make a post</a>

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