<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty requestScope.purchases}">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" <c:out value="${requestScope.page > 0 ? 'href' : ''}" />
            ="${request.requestURL}?page=${requestScope.page - 1}">Previous</a>
        </li>
        <c:forEach begin="0" end="${requestScope.numOfPages}" varStatus="page">
            <li class="page-item">
                <a class="page-link <c:out value="${page.index == requestScope.page ? 'active' : ''}" />"
                   href="${request.requestURL}?page=${page.index}">${page.index + 1}</a>
            </li>
        </c:forEach>
        <li class="page-item ">
            <a class="page-link" <c:out value="${requestScope.page < requestScope.numOfPages ? 'href' : ''}" />
            ="${request.requestURL}?page=${requestScope.page + 1}">Next</a>
        </li>
    </ul>
</c:if>