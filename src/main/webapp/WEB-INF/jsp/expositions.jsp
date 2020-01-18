<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<div class="expositions">
    <div class="container-fluid">
        <h2>${requestScope.hall.title}</h2>
        <div class="row">
            <c:forEach items="${requestScope.expositions}" var="exposition">
                <div class="col-md-4">
                    <div class="card">
                        <img class="card-img-top" src="${exposition.imagePath}">
                        <div class="card-body">
                            <h4 class="card-title">${exposition.title}</h4>
                            <p>${exposition.description}</p>
                            <p>Takes place: ${exposition.startDate} - ${exposition.endDate}</p>
                            <a class="btn btn-lg btn-info btn-block" href="">
                                Buy Ticket
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
