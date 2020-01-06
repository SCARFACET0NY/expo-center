<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>
<html>
<body>
    <a href="?locale=en">en</a>
    <a href="?locale=ru">ru</a>
    <a href="?locale=ua">ua</a>
    <h1><fmt:message bundle="${message}" key="expo.title"/></h1>
</body>
</html>
