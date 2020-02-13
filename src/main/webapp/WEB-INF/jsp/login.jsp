<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div class="login">
        <div class="container">
            <h2><fmt:message bundle="${message}" key="expo.login"/></h2>
            <c:if test="${not empty requestScope.invalid}">
                <p class="red"><fmt:message bundle="${message}" key="expo.login.invalid"/></p>
            </c:if>
            <form action="login" method="post" id="loginForm">
                <div class="form-group">
                    <label for="username"><fmt:message bundle="${message}" key="expo.login.username"/>:</label>
                    <input type="text" id="username" class="form-control"
                           placeholder="Enter username"
                           required="required"
                           minlength="3"
                           name="username">
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message bundle="${message}" key="expo.login.password"/>:</label>
                    <input type="password" id="password" class="form-control"
                           placeholder="Enter password"
                           required="required"
                           minlength="5"
                           name="password">
                </div>
                <button type="submit" id="userLogin" class="btn btn-info btn-block">
                    <fmt:message bundle="${message}" key="expo.login"/>
                </button>
                <a href="register" class="btn btn-dark btn-block"><fmt:message bundle="${message}" key="expo.register"/></a>
                <a href="/" class="btn btn-info btn-block"><fmt:message bundle="${message}" key="expo.home"/></a>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

