<%@ page contentType="text/html; charset=UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css" charset="UTF-8">
</head>
<body>
    <div class="container cart">
        <h2 class="cart-header">Tickets</h2>

        <c:if test="${cart != null && not empty cart}">
            <table class="table table-hover">
                <tr class="bg-info text-dark">
                    <th>title</th>
                    <th>date</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>action</th>
                </tr>
                <c:forEach items="${cart.values()}" var="item">
                    <tr class="bg-dark text-light">
                        <td>${item.exposition.title}</td>
                        <td>${item.ticket.date}</td>
                        <td id="price">${item.exposition.price}</td>
                        <td>
                            <form action="setQuantity" method="post">
                                <input type="hidden" name="exposition_id" value="${item.exposition.id}">
                                <input name="sign" type="submit" value="-" class="btn btn-info rounded-circle sign">
                                <span>${item.ticket.quantity}</span>
                                <input name="sign" type="submit" value="+" class="btn btn-info rounded-circle sign">
                            </form>
                        </td>
                        <td>
                            <form action="removeTicket" method="post">
                                <input type="hidden" name="exposition_id" value="${item.exposition.id}">
                                <button type="submit" class="btn btn-info">remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p class="total">Total: 0</p>



            <form action="pay" method="post" class="coupon">
                <button type="submit" class="btn btn-lg btn-info btn-block">Pay</button>
            </form>
        </c:if>

        <a class="btn btn-lg btn-dark btn-block home" href="/">Home</a>
    </div>
</body>
</html>
