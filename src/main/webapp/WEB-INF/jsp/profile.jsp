<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="application" var="message"/>

<div class="container">
    <h2>Admin</h2>
    <table class="table table-striped table-dark">
        <tbody>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.register.first"/></td>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.register.last"/></td>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.register.phone"/></td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.register.email"/></td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.profile.date.joined"/></td>
                <td>${user.dateJoined.toLocalDate()}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.register.card.number"/></td>
                <td>${user.cardNumber}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.login.username"/></td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${message}" key="expo.profile.status"/></td>
                <td>${user.accountStatus}</td>
            </tr>
        </tbody>
    </table>
</div>
