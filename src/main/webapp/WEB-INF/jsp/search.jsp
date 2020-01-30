<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="sub-header.jsp"/>
    <div class="search">
        <div class="container">
            <h2>Search Expositions</h2>
            <form method="post" action="/search">
                <div class="row">
                    <div class="col-md-9">
                        <input type="text" class="form-control" name="query">
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-lg btn-info form-control">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="container-fluid">
        <c:if test="${not empty requestScope.emptySearch}">
            <h2>${requestScope.emptySearch}</h2>
        </c:if>
        <jsp:include page="expositions.jsp"/>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
