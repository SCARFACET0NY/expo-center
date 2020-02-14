<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="application" var="message"/>

<div id="accordion">
    <c:forEach items="${requestScope.purchases}" var="purchase" varStatus="loop">
        <fmt:parseDate  value="${purchase.date}" type="date" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate"/>
        <tr>
            <div class="card">
                <div class="card-header">
                    <a class="card-link " data-toggle="collapse" href="#collapse${loop.index}">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col">
                                    <fmt:message bundle="${message}" key="expo.number"/>: ${loop.index + 1}
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${message}" key="expo.register.card.number"/>: ${purchase.cardNumber}
                                </div>
                                <div class="col"><fmt:message bundle="${message}" key="expo.ticket.date"/>:
                                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${message}" key="expo.total"/>: ${purchase.total}
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div id="collapse${loop.index}" class="collapse" data-parent="#accordion">
                    <table class="table table-striped">
                        <tr>
                            <th><fmt:message bundle="${message}" key="expo.exposition.title"/></th>
                            <th><fmt:message bundle="${message}" key="expo.ticket.date"/></th>
                            <th><fmt:message bundle="${message}" key="expo.ticket.quantity"/></th>
                            <th><fmt:message bundle="${message}" key="expo.exposition.price"/></th>
                        </tr>
                        <c:forEach items="${purchase.tickets}" var="ticket">
                            <tr>
                                <td>${ticket.exposition.title}</td>
                                <td>${ticket.ticket.date}</td>
                                <td>${ticket.ticket.quantity}</td>
                                <td>${ticket.exposition.price}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </tr>
    </c:forEach>
</div>
