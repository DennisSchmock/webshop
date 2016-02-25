<%-- 
    Document   : cart
    Created on : 20-02-2016, 19:58:28
    Author     : dennisschmock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="header.jsp" %>
<main>
   
        <table class="shoppingcart"><tr><th>Topping</th><th>Bottom</th><th>Price</th><th>QTY</th><th>Total</th><th>subtract/add/remove</th>
                <c:if test="${fn:length(cart)>0}">   
                <c:forEach var="cart" items="${sessionScope.cart}"  varStatus="theCount">
                
                <tr><td>${cart.bottom.name} </td><td>${cart.topping.name}</td><td>${cart.price} kr.</td><td>${cart.qty} piece(s)</td><td> ${cart.price*cart.qty}</td>
                    <td> <form class="checkout" name="CartControl" action="webshop" method="POST">
                            <input type="hidden" name="page" value="cart" />
                            <input type="hidden" name="itemNumber" value="${theCount.count-1}" />
                            <input type="submit" value="-" name="controlCart" />
                            <input type="submit" value="+" name="controlCart" />
                            <input type="submit" value="%" name="controlCart"/>                
                        </form>

                    </td></tr>


            </c:forEach>
            
            <tr><td colspan="6">
                          
                    <c:if test="${sessionScope.notEnoughMoney==true}">Sorry, there is not enough money! Refill your credit here: <a href="?page=refill">Refill Credits</a> </c:if>

                    <form name="going out" action="webshop" method="POST">
                        <input type="hidden" name="page" value="checkout" />
                        <input type="submit" value="Check Out" name="checkout" />

                    </form>
                </td></tr>
            </c:if>
        </table>
</main>
<%@include file="footer.jsp" %>