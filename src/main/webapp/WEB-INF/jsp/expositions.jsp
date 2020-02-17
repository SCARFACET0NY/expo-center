<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<div class="row">
    <c:forEach items="${requestScope.expositions}" var="exposition">
        <div class="col-md-4">
            <div class="card">
                <img class="card-img-top" src="${exposition.imagePath}">
                <div class="card-body">
                    <h4 class="card-title">${exposition.title}</h4>
                    <p>${exposition.description}</p>
                    <p><fmt:message bundle="${message}" key="expo.exposition.price"/>:
                            ${exposition.price} <fmt:message bundle="${message}" key="expo.uah"/>
                    </p>
                    <p><fmt:message bundle="${message}" key="expo.exposition.time"/>: ${exposition.startDate} - ${exposition.endDate}</p>
                    <c:choose>
                        <c:when test="${not empty user}">
                            <form action="addTicket" method="post">
                                <input type ="hidden" name="exposition_id" value="${exposition.id}">
                                <button class="btn btn-lg btn-info btn-block" type="submit">
                                    <fmt:message bundle="${message}" key="expo.exposition.ticket"/>
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-lg btn-info btn-block" href="/login">
                                <fmt:message bundle="${message}" key="expo.exposition.ticket"/>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>
</div>