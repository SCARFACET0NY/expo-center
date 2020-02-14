<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/main.css" charset="UTF-8">
</head>
<body>
    <div class="container">
        <h2><fmt:message bundle="${message}" key="expo.exposition.add"/></h2>
        <form action="/addExposition" method="post">
            <div class="form-group">
                <label for="title"><fmt:message bundle="${message}" key="expo.exposition.title"/>:</label>
                <input type="text" id="title" class="form-control" name="title">
            </div>
            <div class="form-group">
                <label for="description"><fmt:message bundle="${message}" key="expo.exposition.description"/>:</label>
                <textarea type="text" id="description" class="form-control" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="price"><fmt:message bundle="${message}" key="expo.exposition.price"/>:</label>
                <input type="number" id="price" class="form-control" step="any" name="price">
            </div>
            <div class="form-group">
                <label for="image_path"><fmt:message bundle="${message}" key="expo.exposition.image.path"/>:</label>
                <input type="text" id="image_path" class="form-control" name="image_path">
            </div>
            <div class="form-group">
                <label for="start_date"><fmt:message bundle="${message}" key="expo.exposition.date.start"/>:</label>
                <input type="date"  id="start_date" name="start_date" >
            </div>
            <div class="form-group">
                <label for="end_date"><fmt:message bundle="${message}" key="expo.exposition.date.end"/>:</label>
                <input type="date"  id="end_date" name="end_date" >
            </div>
            <div class="form-group">
                <label for="halls"><fmt:message bundle="${message}" key="expo.hall.choose"/>:</label>
                <select name="hall_id" id="halls" class="form-control">
                    <c:forEach items="${requestScope.halls}" var="hall">
                        <option value="${hall.id}">${hall.title}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" id="userLogin" class="btn btn-info btn-block">
                <fmt:message bundle="${message}" key="expo.add"/>
            </button>
            <a class="btn btn-dark btn-block" href="/admin"><fmt:message bundle="${message}" key="expo.admin"/></a>
        </form>
    </div>
</body>
</html>
