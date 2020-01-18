<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<footer class="navbar navbar-expand-sm bg-dark navbar-dark
    <c:out value="${sessionScope.employee != null ? 'fixed' : ''}"/>">
    <fmt:setBundle basename="application" var="message"/>
    <div class="logo">
        <a href="/" class="nav-link text-light">
            <img src="img/center_icon_64.png" width="32" height="32"/>
            <span><fmt:message bundle="${message}" key="expo.title"/></span>
        </a>
    </div>
    <div class="copyright">
        <span class="text-light">
            © 2019 <fmt:message bundle="${message}" key="expo.developer"/>:
            <fmt:message bundle="${message}" key="expo.author"/>
        </span>
    </div>
    <a class="github text-light nav-link" href="https://github.com/SCARFACET0NY/expo-center">
        <i class="fa fa-github" style="font-size: 32px;"></i>
        <span><fmt:message bundle="${message}" key="expo.github"/></span>
    </a>
</footer>