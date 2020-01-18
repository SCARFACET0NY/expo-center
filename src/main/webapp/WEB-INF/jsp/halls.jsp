<%@ page contentType="text/html; charset=UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<div class="halls">
    <div class="container-fluid">
        <h2><fmt:message bundle="${message}" key="expo.halls.title"/></h2>

        <div class="row">
            <c:forEach items="${halls}" var="hall">
                <div class="col-md-4">
                    <div class="card">
                        <img class="card-img-top" src="${hall.imagePath}">
                        <div class="card-body">
                            <h4 class="card-title">${hall.title}</h4>
                            <p><fmt:message bundle="${message}" key="expo.halls.area"/>: ${hall.area}</p>
                            <a class="btn btn-lg btn-info btn-block" href="${hall.type.toString().toLowerCase()}Hall">
                                <fmt:message bundle="${message}" key="expo.halls.button.expositions"/>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
