<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Post</title>
</head>
<body>

<jsp:useBean id="post" scope="request" type="ru.job4j.cars.model.Post"/>
<div class="container pt-1">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <c:choose>
                    <c:when test="${post.id == 0}">
                        Create post
                    </c:when>
                    <c:otherwise>
                        Edit post
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-body">
                <form action="<c:url value='/post.do'/>" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${post.id}">

                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="description">Description</label>
                        <input type="text" class="form-control col-sm-3" name="description" id="description"
                               value="${post.description}" placeholder="Input post description">
                    </div>

                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="sold">Sold</label>
                        <input type="checkbox" name="sold" id="sold"
                               <c:if test="${post.sold}">checked="checked"</c:if>
                        />
                    </div>

                    <div class="form-group row">
                        <jsp:useBean id="brands" scope="request" type="java.util.List<ru.job4j.cars.model.CarBrand>"/>
                        <label class="col-form-label col-sm-3" for="brands">Brand</label>
                        <select name="brand-id" class="form-control col-sm-3" id="brands">
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${brand == post.carBrand? 'selected' : ''}> ${brand.name} </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group row">
                        <jsp:useBean id="bodies" scope="request"
                                     type="java.util.List<ru.job4j.cars.model.CarBodyType>"/>
                        <label class="col-form-label col-sm-3" for="bodies">Body type</label>
                        <select name="body-id" class="form-control col-sm-3" id="bodies">
                            <c:forEach items="${bodies}" var="body">
                                <option value="${body.id}" ${body == post.carBodyType? 'selected' : ''}> ${body.name} </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-3">
                            <input type="file" id="file" name="file" accept="images" multiple/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <c:forEach items="${post.images}" var="image">
                            <img src="<c:url value="/image.do?path=${image.path}"/>" width="200px" height="150px">
                        </c:forEach>
                    </div>

                    <div class="form-group row">
                        <label class="col-form-label col-sm-3"></label>
                        <button type="submit" class="btn btn-dark">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>