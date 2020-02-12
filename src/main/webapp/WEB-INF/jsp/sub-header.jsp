<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<nav class="navbar navbar-expand-sm bg-info justify-content-center sub-header">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link ${requestScope['javax.servlet.forward.request_uri'].equals("/largeHall")
                                 ? "text-light" : ""}" href="/largeHall">
                <img class="icon" src="img/hall_large_icon.png"/>
                <span><fmt:message bundle="${message}" key="expo.hall.large"/></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${requestScope['javax.servlet.forward.request_uri'].equals("/mediumHall")
                                 ? "text-light" : ""}" href="/mediumHall">
                <img class="icon" src="img/hall_medium_icon.png"/>
                <span><fmt:message bundle="${message}" key="expo.hall.medium"/></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${requestScope['javax.servlet.forward.request_uri'].equals("/smallHall")
                                 ? "text-light" : ""}" href="/smallHall">
                <img class="icon" src="img/hall_small_icon.png"/>
                <span><fmt:message bundle="${message}" key="expo.hall.small"/></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${requestScope['javax.servlet.forward.request_uri'].equals("/search")
                                 ? "text-light" : ""}" href="/search">
                <img class="icon" src="img/search_icon.png"/>
                <span><fmt:message bundle="${message}" key="expo.search"/></span>
            </a>
        </li>
    </ul>
</nav>
