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
        <h2>Add Exposition</h2>
        <form action="addExposition" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" class="form-control"
                   value=""
                   name="title">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea type="text" id="description" class="form-control"
                          value="" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" class="form-control" step="any"
                       value=""
                       name="price">
            </div>
            <div class="form-group">
                <label for="image_path">Image Path:</label>
                <input type="text" id="image_path" class="form-control"
                       value=""
                       name="image_path">
            </div>
            <div class="form-group">
                <label for="start_date">Start Date:</label>
                <input type="date"  id="start_date"
                       value=""
                       name="start_date" >
            </div>
            <div class="form-group">
                <label for="end_date">End Date:</label>
                <input type="date"  id="end_date"
                       value=""
                       name="end_date" >
            </div>
            <div class="form-group">
                <label for="halls">Choose Hall:</label>
                <select name="hall_id" id="halls" class="form-control">
                    <c:forEach items="${requestScope.halls}" var="hall">
                        <option value="${hall.id}">${hall.title}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" id="userLogin" class="btn btn-info btn-block">Update</button>
            <a class="btn btn-dark btn-block" href="/admin">Admin</a>
        </form>
    </div>
</body>
</html>
