<%@ page contentType="text/html; charset=UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <h2>Purchases</h2>

    <div id="accordion">
        <tr>
            <div class="card">
                <div class="card-header">
                    <a class="card-link " data-toggle="collapse" href="#collapse1">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col">
                                    Order:
                                </div>
                                <div class="col">
                                    Date:
                                </div>
                                <div class="col">
                                    Status:
                                </div>
                                <div class="col">
                                    Coupon:
                                </div>
                                <div class="col">
                                    Discount:
                                </div>
                                <div class="col">
                                    Total:
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div id="collapse1" class="collapse" data-parent="#accordion">
                    <table class="table table-striped">
                        <tr>
                            <th>title</th>
                            <th>description</th>
                            <th>price</th>
                            <th>quantity</th>
                        </tr>
                        <tr>
                            <td>title</td>
                            <td>description</td>
                            <td>price</td>
                            <td>quantity</td>
                        </tr>
                    </table>
                </div>
            </div>
        </tr>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
