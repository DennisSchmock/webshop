<%-- 
    Document   : shop
    Created on : 20-02-2016, 11:40:59
    Author     : dennisschmock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Choose a topping:
        <form action="webshop" method="POST">
            <select>
                <c:forEach var="Topping" items="${sessionScope.toppings}">
                    <option value="${Topping.id}">${Topping.name}, ${Topping.price} kr.</option>
                </c:forEach>   
            </select>
            Choose a Bottom:
            <select>
                <c:forEach var="Bottom" items="${sessionScope.bottoms}">
                    <option value="${Bottom.id}">${Bottom.name}, ${Bottom.price} kr.</option>
                </c:forEach> 
                
            </select>
                <input type="hidden" name="page" value="shop" />
                <input type="submit" value="Make Cupcake" name="cupcake" />
        </form>

    </body>
</html>
