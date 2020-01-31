<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
    <h2>Admin</h2>
    <table class="table table-striped table-dark">
        <tbody>
            <tr>
                <td>First Name</td>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>Date Joined</td>
                <td>${user.dateJoined.toLocalDate()}</td>
            </tr>
            <tr>
                <td>Card Number</td>
                <td>${user.cardNumber}</td>
            </tr>
            <tr>
                <td>Username</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>Status</td>
                <td>${user.accountStatus}</td>
            </tr>
        </tbody>
    </table>
</div>
