<%-- 
    Document   : index
    Created on : 18-02-2016, 10:51:03
    Author     : dennisschmock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:if test="${sessionScope.loggedin==true}">
            Hello <c:out value="${sessionScope.user.fName}"/>
            <c:out value="${sessionScope.user.lName}"/>.
            <a href="webshop?logout=true">logout</a>
        </c:if>
        <c:if test="${sessionScope.loggedin!=true}">

            <form action="webshop" method="POST">
                <input type="text" name="username" value="" />
                <input type="password" name="pwd" value="" />
                <input type="submit" name="login" value="Login" />
                <c:if test="${sessionScope.loggedin!=null}">Wrong username or password!</c:if>

                </form>
        </c:if>
            
            <a href="webshop?page=shop">shop</a>;
            
            



    </body>
</html>
