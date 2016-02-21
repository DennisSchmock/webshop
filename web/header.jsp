<!DOCTYPE html>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CupCake Heaven</title>
        <style>
            header{
                background: darkblue;
                color: lightcyan;
                text-align:left;
                padding:5px;
                float: start;
                height: 150px;
                font-size: 15px;


            }
            header.h1{
                color:lightcyan;

            }
            header.a:link,header.a:visited{
                color: darkslateblue;
                text-decoration: none;

            }
            body{
                margin: 0;
                font-size: 15px;
                font-family: fantasy;
                background-color: lightcyan;
            }
            nav{
                background: #000;
                color: graytext;
                margin: 0;
                overflow: hidden;
                float: top;
                width: 100%;
                height: 30px;
                padding: 10px;
            }
            footer{
                background: darkblue;
                color: lightcyan;
                text-align: center;
                float: end;
                clear: both;


            }
            main{
                padding: 10px;
                background: lightcyan;
                width: 100%;
                height: 800px;
                alignment-adjust: central;

            }
            nav ul{
                margin: 0;
                padding: 0;
            }
            nav ul li {
                display: inline-block;
                list-style-type: none;
            }

            nav > ul > li > a {
                color: #black;
                background-color: aliceblue;
                display: block;
                line-height: 2em;
                padding: 0.5em 0.5em;
                text-decoration: none;
                
            }
            nav a:hover{
                background-color: blue;
                color: white;
                    
            }
            form{
                font-family: fantasy;
                font-size: 15px;
            }
            input{
                width: 200px;
                height: 40px;
                font-size: large;
                border: 1px solid black;

            }
            button{
                width: 200px;
                height: 40px;
                font-size: large; 
                color: black;
                border: none
            }
            td.b1{
                text-align: center;
            }
            .userlist th  {
                border-bottom:  1px solid black;
            }

            section.relative{
                position: absolute;
                top: 90px;
                right: 0px;
                width: 300px;
                color: black;

                height: 30px;
                background-color: lightskyblue;
                padding: 10px;
                margin: 10px;

            }

            .bottoms{
                float: left;
                margin:  10px;
            }
            .toppings{
                float: left;
                margin: 10px;
            }
            .bottoms table{
                border: 1px solid black;
                border-collapse: collapse;
                padding: 5px;
            }
            .toppings table{
                border: 1px solid black;
                border-collapse: collapse;
                background-color: white;
            }
            .toppings td{
                border-bottom: 1px solid black;
                padding: 5px;

            }
            .bottoms td{
                border-bottom: 1px solid black;
                padding: 5px;

            }
            .addcupcake{
                float: end;
            }
            .cart{
                position: absolute;
                top: 0px;
                right:  0px;
                background-color: lightcyan;
                width: 300px;
                height: 100 px;
                color: black;
                margin: 10px;
                padding: 10px;
            }
            .login{
                float:next;
                padding: 20px;
            }
            .checkout input{
                height: 30px;
                width: 30px;
            }
            .shoppingcart table{
                border-collapse: collapse;
                border-spacing: 0;
            }
            .shoppingcart th{
                font-weight: bold;
                border: 1px solid black;
                font-size: 20px;
                background-color: lightgray;
            }
            .shoppingcart td{
                border: 1px solid black;
                font-size: 20px;
                min-width: 120px;
                text-align: right;
                padding: 5px;
                
            }


        </style>
    </head>
    <body><header><h1>CupCake Heaven</h1>
            <section class="relative"> 
                <c:if test="${sessionScope.loggedin==true}">
                    Hello <c:out value="${sessionScope.user.fName}"/>
                    <c:out value="${sessionScope.user.lName}"/>.
                    Balance: ${sessionScope.user.balance} kr.
                    <a href="webshop?logout=true">logout</a>
                </c:if>
                <c:if test="${sessionScope.loggedin!=true}">
                    No user logged in.<br>
                    <a href="webshop?page=login"> Click here to login.</a>


                </c:if>
            </section>
            <section class="cart">
                You have ${fn:length(cart)} cupcakes in your shopping cart.<br>
                Total price: ${sessionScope.totalprice}
                <c:if test="${sessionScope.loggedin==true}">
                    <a href="webshop?page=cart">View Cart</a>
                </c:if>
                <c:if test="${sessionScope.loggedin!=true}">
                    <br>Log in to view cart!
                </c:if>
            </section>
        </header>
        <nav>
            <ul>
                <li><a href="webshop?page=index">Front Page</a></li>

                <li><a href="webshop?page=shop">Visit shop</a></li>
            </ul>
        </nav>
