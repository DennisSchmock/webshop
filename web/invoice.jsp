<%-- 
    Document   : invoice
    Created on : 21-02-2016, 00:02:40
    Author     : dennisschmock
--%>



<%@include file="header.jsp" %>



<h1>Invoice</h1>
<table class="shoppingcart"><tr><th>Topping</th><th>Bottom</th><th>QTY</th><th>Price</th><th>Total</th></tr>

        <c:forEach var="basket" items="${sessionScope.basket}"  varStatus="theCount">
            
            <tr><td>${basket.topping.name}</td><td>${basket.bottom.name}</td><td>${basket.qty}</td><td>${basket.price}</td><td>${basket.price*basket.qty}</td></tr>


        </c:forEach>
            <tr><td colspan="4">Total sum</td><td>${sessionScope.totalPrice}</td></tr>
</table>




        <%@include file="footer.jsp" %>

