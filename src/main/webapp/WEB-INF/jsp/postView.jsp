<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Create Post</title>
</head>
<body>

<jsp:useBean id="post" scope="request" type="ru.job4j.cars.model.Post"/>
<div class="container pt-1">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Post
            </div>
            <div class="card-body">
                <div class="form-group col">
                    <h5 class="card-title">${post.description}</h5>
                    <p>Sold: ${post.sold}</p>
                    <p>Brand: ${post.carBrand.name}</p>
                    <p>Body type: ${post.carBodyType.name}</p>
                </div>
                <div class="form-group col">
                    <label class="col-form-label col-sm-3">Images</label>
                    <c:forEach items="${post.images}" var="image">
                        <img src="<c:url value="/image.do?path=${image.path}"/>" width="300px" height="200px">
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>