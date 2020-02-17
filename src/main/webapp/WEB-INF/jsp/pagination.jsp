<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="application" var="message"/>

<c:if test="${not empty requestScope.purchases}">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" <c:out value="${requestScope.page > 0 ? 'href' : ''}" />
            ="${request.requestURL}?page=${requestScope.page - 1}">
                <fmt:message bundle="${message}" key="expo.page.previous"/>
            </a>
        </li>
        <c:forEach begin="0" end="${requestScope.numOfPages}" varStatus="page">
            <li class="page-item">
                <a class="page-link <c:out value="${page.index == requestScope.page ? 'active' : ''}" />"
                   href="${request.requestURL}?page=${page.index}">${page.index + 1}</a>
            </li>
        </c:forEach>
        <li class="page-item ">
            <a class="page-link" <c:out value="${requestScope.page < requestScope.numOfPages ? 'href' : ''}" />
            ="${request.requestURL}?page=${requestScope.page + 1}">
                <fmt:message bundle="${message}" key="expo.page.next"/>
            </a>
        </li>
    </ul>
</c:if>