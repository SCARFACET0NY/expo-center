<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css" charset="UTF-8">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="sub-header.jsp"/>
    <div class="search">
        <div class="container">
            <h2>Search Expositions</h2>
            <form method="post" action="/searchExpositions">
                <div class="row">
                    <div class="col-md-9">
                        <input type="text" class="form-control">
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-lg btn-info form-control">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
