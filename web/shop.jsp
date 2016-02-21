<%-- 
    Document   : shop
    Created on : 20-02-2016, 11:40:59
    Author     : dennisschmock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="header.jsp" %><main>
    <h1>Build your CupCake!</h1>
    <section class="toppings">
        <form action="webshop" method="POST">
            <table class="toppings"><tr><td colspan="3"><h2>Toppings</h2></tr>

                <c:forEach var="Topping" items="${sessionScope.toppings}">
                    <tr><td> <input type="radio" name="topping_choice" value="${Topping.id}" checked="checked" /></td><td>${Topping.name}</td><td>${Topping.price} kr.</td></tr>
                </c:forEach>   
            </table>

            <table class="bottoms"><tr><td colspan="3"><h2>Bottoms</h2></tr>
                <c:forEach var="Bottom" items="${sessionScope.bottoms}">
                    <tr><td><input type="radio" name="bottom_choice" value="${Bottom.id}" checked="checked"/></td><td>${Bottom.name}</td><td> ${Bottom.price} kr.</td><tr>

                </c:forEach> 
                    <tr><td></td><td colspan="2" align="right"><input type="hidden" name="page" value="shop" />
                <input type="submit" value="Make Cupcake" name="cupcake" /></td>
            </table>
           
        </form>

    </section>
</main>



<%@include file="footer.jsp" %>
