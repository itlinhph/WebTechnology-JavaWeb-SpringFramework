<%-- 
    Document   : index
    Created on : Mar 13, 2017, 2:16:11 PM
    Author     : linhphan
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
        User user = null;
        
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
 %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sale Web</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

        <script type ="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"> </script>

        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 

        
    </head>

    ${script}
    <body>
        
        <%if (user != null) {%>
            <jsp:include page="user/userheader.jsp"></jsp:include>
        <% } else { %>
            <jsp:include page="header.jsp"></jsp:include>
        <% } %>
        <jsp:include page="content.jsp"></jsp:include>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
