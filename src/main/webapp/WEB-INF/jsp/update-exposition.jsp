<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/main.css" charset="UTF-8">
</head>
<body>
    <div class="container">
        <h2>Choose Exposition</h2>
        <form action="/chooseExposition" method="get">
            <div class="form-group">
                <label for="expositions">Choose Exposition:</label>
                <select name="exposition_id" id="expositions" class="form-control">
                    <c:forEach items="${requestScope.expositions}" var="exposition">
                        <option value="${exposition.id}"
                            ${exposition.id == requestScope.exposition.id ? 'selected' : ''}>${exposition.title}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-info btn-block">Choose</button>
        </form>
        <hr/>

        <c:if test="${not empty requestScope.exposition}">
            <h2>Update Exposition</h2>
            <form action="/updateExposition" method="post">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="hidden" name="exposition_id" value="${requestScope.exposition.id}"/>
                    <input type="text" id="title" class="form-control"
                           value="${requestScope.exposition.title}"
                           name="title">
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea type="text" id="description" class="form-control"
                              name="description">${requestScope.exposition.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" id="price" class="form-control" step="any"
                           value="${requestScope.exposition.price}"
                           name="price">
                </div>
                <div class="form-group">
                    <label for="image_path">Image Path:</label>
                    <input type="text" id="image_path" class="form-control"
                           value="${requestScope.exposition.imagePath}"
                           name="image_path">
                </div>
                <div class="form-group">
                    <label for="start_date">Start Date:</label>
                    <input type="date"  id="start_date"
                           value="${requestScope.exposition.startDate}"
                           name="start_date" >
                </div>
                <div class="form-group">
                    <label for="end_date">End Date:</label>
                    <input type="date"  id="end_date"
                           value="${requestScope.exposition.endDate}"
                           name="end_date" >
                </div>
                <div class="form-group">
                    <label for="halls">Choose Hall:</label>
                    <select name="hall_id" id="halls" class="form-control">
                        <c:forEach items="${requestScope.halls}" var="hall">
                            <option value="${hall.id}" ${requestScope.exposition.hallId == hall.id ? 'selected' : ''}>
                                    ${hall.title}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-info btn-block">Update</button>
                <a class="btn btn-dark btn-block" href="/admin">Admin</a>
            </form>
        </c:if>
    </div>
</body>
</html>
