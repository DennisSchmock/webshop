<%-- 
    Document   : login
    Created on : 21-02-2016, 02:46:03
    Author     : dennisschmock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<body>
    <h1>Login!</h1>
    <h2> <c:if test="${sessionScope.loggedin==true}">
        Hello <c:out value="${sessionScope.user.fName}"/>
        <c:out value="${sessionScope.user.lName}"/>.
      
        <br>
        You are now logged in. <br>now go visit our <a href="?page=shop">CupCake Heaven.</a>
       
    </c:if>
    <c:if test="${sessionScope.loggedin!=true}">
        </h2>

        <form class ="login" action="webshop" method="POST">
            Username: <input type="text" name="username" value="" /><br><br>
            Password: <input type="password" name="pwd" value="" /><br><br>
            <input type="submit" name="login" value="Login" /><br>
            <input type="hidden" name="page" value="login" />
            <c:if test="${sessionScope.loggedin!=null}"><br>Wrong username or password!</c:if>

            </form>
    </c:if>
<%@include file="footer.jsp" %>
