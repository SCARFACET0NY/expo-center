<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <div class="dropdown">
            <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
                <c:out value="${sessionScope.lang == null ? 'en' : sessionScope.lang}"/>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="?locale=en">en</a>
                <a class="dropdown-item" href="?locale=ru">ru</a>
                <a class="dropdown-item" href="?locale=ua">ua</a>
            </div>
        </div>
        <div class="logo">
            <a href="/" class="nav-link text-light">
                <img src="img/center_icon_64.png"/>
                <span><fmt:message bundle="${message}" key="expo.title"/></span>
            </a>
        </div>

        <c:choose>
            <c:when test="${not empty user}">
                <div class="btn-group btn-group-lg">
                    <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                            <span>${not empty total ? total : '0.00'} uah</span><br/>
                            <span>${not empty cart ? cart.size() : 0} tickets</span>
                        </button>
                        <div class="dropdown-menu">
                            <c:forEach items="${cart.values()}" var="ticket">
                                <span class="dropdown-item">${ticket.exposition.title} : ${ticket.exposition.price}</span>
                                <div class="dropdown-divider"></div>
                            </c:forEach>
                            <span class="dropdown-item">total: ${sessionScope.total}</span>
                        </div>
                    </div>

                    <button type="button" class="btn btn-info">
                        <a href="cart" class="nav-link text-light">Checkout</a>
                    </button>
                </div>
                <a type="button" class="btn btn-light btn-lg" href="/logout">
                    <fmt:message bundle="${message}" key="expo.logout"/>
                </a>
            </c:when>
            <c:otherwise>
                <a type="button" class="btn btn-light btn-lg" href="/login">
                    <fmt:message bundle="${message}" key="expo.login"/>
                </a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
