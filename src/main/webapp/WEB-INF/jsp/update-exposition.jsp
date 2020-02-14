<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
    <div class="container">
        <h2><fmt:message bundle="${message}" key="expo.exposition.update"/></h2>
        <form action="/chooseExposition" method="get">
            <div class="form-group">
                <label for="expositions"><fmt:message bundle="${message}" key="expo.exposition.choose"/>:</label>
                <select name="exposition_id" id="expositions" class="form-control">
                    <c:forEach items="${requestScope.expositions}" var="exposition">
                        <option value="${exposition.id}"
                            ${exposition.id == requestScope.exposition.id ? 'selected' : ''}>${exposition.title}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-info btn-block">
                <fmt:message bundle="${message}" key="expo.choose"/>
            </button>
        </form>
        <hr/>

        <c:if test="${not empty requestScope.exposition}">
            <form action="/updateExposition" method="post">
                <div class="form-group">
                    <label for="title"><fmt:message bundle="${message}" key="expo.exposition.title"/>:</label>
                    <input type="hidden" name="exposition_id" value="${requestScope.exposition.id}"/>
                    <input type="text" id="title" class="form-control"
                           value="${requestScope.exposition.title}"
                           name="title">
                </div>
                <div class="form-group">
                    <label for="description"><fmt:message bundle="${message}" key="expo.exposition.description"/>:</label>
                    <textarea type="text" id="description" class="form-control"
                              name="description">${requestScope.exposition.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="price"><fmt:message bundle="${message}" key="expo.exposition.price"/>:</label>
                    <input type="number" id="price" class="form-control" step="any"
                           value="${requestScope.exposition.price}"
                           name="price">
                </div>
                <div class="form-group">
                    <label for="image_path"><fmt:message bundle="${message}" key="expo.exposition.image.path"/>:</label>
                    <input type="text" id="image_path" class="form-control"
                           value="${requestScope.exposition.imagePath}"
                           name="image_path">
                </div>
                <div class="form-group">
                    <label for="start_date"><fmt:message bundle="${message}" key="expo.exposition.date.start"/>:</label>
                    <input type="date"  id="start_date"
                           value="${requestScope.exposition.startDate}"
                           name="start_date" >
                </div>
                <div class="form-group">
                    <label for="end_date"><fmt:message bundle="${message}" key="expo.exposition.date.end"/>:</label>
                    <input type="date"  id="end_date"
                           value="${requestScope.exposition.endDate}"
                           name="end_date" >
                </div>
                <div class="form-group">
                    <label for="halls"><fmt:message bundle="${message}" key="expo.hall.choose"/>:</label>
                    <select name="hall_id" id="halls" class="form-control">
                        <c:forEach items="${requestScope.halls}" var="hall">
                            <option value="${hall.id}" ${requestScope.exposition.hallId == hall.id ? 'selected' : ''}>
                                    ${hall.title}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-info btn-block">
                    <fmt:message bundle="${message}" key="expo.update"/>
                </button>
            </form>
        </c:if>
        <a class="btn btn-dark btn-block" href="/admin"><fmt:message bundle="${message}" key="expo.admin"/></a>
    </div>
</body>
</html>
