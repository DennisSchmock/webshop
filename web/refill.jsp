<%@include file="header.jsp" %>
<h1>Refill credits</h1>
<c:if test="${sessionScore.payComplete!=true||sessionScore.payComplete==null}"><input type="hidden" name="page" value="refill" />
<form class="refill" name="refillcreds" action="webshop" method="POST">
    <table>
        <tr><td>Get 100 credit</td><td><input type="radio" name="refillcreds" value="100" ></td></tr>
        <tr><td>Get 200 credit</td><td><input type="radio" name="refillcreds" value="200" ></td></tr>
        <tr><td>Get 300 credit</td><td><input type="radio" name="refillcreds" value="300" ></td></tr>
        <tr><td>Get 400 credit</td><td><input type="radio" name="refillcreds" value="400" ></td></tr>
        <tr><td>Get 500 credit</td><td><input type="radio" name="refillcreds" value="500" ></td></tr>
    </table>
    <input type="hidden" name="page" value="refill" />
    <input type="submit" value="CreditCard" onclick="${sessionScope.payComplete==true}" />
    <input type="submit" value="Mail Order" onClick="${sessionScope.payComplete==true}"/>


    
</c:if>
    
    <c:if test="${sessionScope.payComplete==true}">
        <h1>Thank you for renewing your credits. You now have ${sessionScope.user.balance} credits</h1>
        <c:set var="payComplete" scope="session" value="false"/>
    </c:if>
        







</form>

<%@include file="footer.jsp" %>
