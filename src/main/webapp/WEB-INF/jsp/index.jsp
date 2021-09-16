<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <title>Todo App</title>
</head>
<body>

<jsp:useBean id="user" scope="session" type="ru.job4j.cars.model.User"/>
<div class="container pt-3">

    <ul class="nav nav-justified">
        <li class="nav-item">
            <a class="nav-link active" href="<c:url value="/post.do"/>">Add new post</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/login.do"/>">
                Logout [<c:out value="${user.name}"/>]
            </a>
        </li>
    </ul>

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">Posts</div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>description</th>
                        <th>photo</th>
                        <th>sold</th>
                        <th>brand</th>
                        <th>body</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="posts" scope="request" type="java.util.List<ru.job4j.cars.model.Post>"/>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td>${post.id} </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/post.do?id=${post.id}">${post.description}</a>
                            </td>
                            <td><c:if test="${!empty post.images}">✓</c:if></td>
                            <td><c:if test="${post.sold}">✓</c:if></td>
                            <td>${post.carBrand.name}</td>
                            <td>${post.carBodyType.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>