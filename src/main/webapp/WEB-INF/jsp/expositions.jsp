<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<div>
    <c:forEach items="${expositions}" var="exposition">
        <h3>${exposition.title}</h3>
        <p>${exposition.description}</p>
        <p>${exposition.imagePath}</p>
        <p>${exposition.startDate}</p>
        <p>${exposition.endDate}</p>
    </c:forEach>
</div>
