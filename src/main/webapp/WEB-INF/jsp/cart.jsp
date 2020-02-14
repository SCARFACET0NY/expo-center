<%@ page contentType="text/html; charset=UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <title><fmt:message bundle="${message}" key="expo.cart"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div class="container-fluid cart">
        <h2 class="cart-header"><fmt:message bundle="${message}" key="expo.tickets.large"/></h2>

        <c:if test="${not empty cart}">
            <table class="table table-hover">
                <tr class="bg-info text-dark">
                    <th><fmt:message bundle="${message}" key="expo.exposition.title"/></th>
                    <th><fmt:message bundle="${message}" key="expo.ticket.date"/></th>
                    <th><fmt:message bundle="${message}" key="expo.exposition.price"/></th>
                    <th><fmt:message bundle="${message}" key="expo.ticket.quantity"/></th>
                    <th><fmt:message bundle="${message}" key="expo.cart.action"/></th>
                </tr>
                <c:forEach items="${cart.values()}" var="item">
                    <tr class="bg-dark text-light">
                        <td>${item.exposition.title}</td>
                        <td>
                            <form action="setDate" method="post">
                                <input type="hidden" name="exposition_id" value="${item.exposition.id}">
                                <input type="date" name="ticket_date" value="${item.ticket.date}"
                                       min="${item.ticket.date}"
                                       max="${item.exposition.endDate}">
                                <button type="submit" class="btn btn-info date">
                                    <fmt:message bundle="${message}" key="expo.cart.set"/>
                                </button>
                            </form>
                        </td>
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
                                <button type="submit" class="btn btn-info">
                                    <fmt:message bundle="${message}" key="expo.cart.remove"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p class="total"><fmt:message bundle="${message}" key="expo.total"/>: ${total}</p>

            <form action="pay" method="post">
                <button type="submit" class="btn btn-lg btn-info btn-block">
                    <fmt:message bundle="${message}" key="expo.cart.pay"/>
                </button>
            </form>
        </c:if>

        <a class="btn btn-lg btn-dark btn-block home" href="/">
            <fmt:message bundle="${message}" key="expo.home"/>
        </a>
    </div>
</body>
</html>
