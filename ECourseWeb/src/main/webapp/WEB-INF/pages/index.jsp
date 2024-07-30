<%-- 
    Document   : index
    Created on : Jul 26, 2024, 9:35:27 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Trang chu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <c:forEach items="${cates}" var="c">
                            <li class="nav-item">
                                <a class="nav-link" href="#">${c.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Id</th>
                        <th>Tên khóa học</th>
                        <th>Gía</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${courses}" var="c">
                        <tr class="table-info" id="course${c.id}">
                            <td>
                                <image width="120" src="${c.image}" alt="${c.name}" />
                            </td>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>${c.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <h1>Hello World!</h1>

        <ul>
            <c:forEach items="${cates}" var="c">
                <li>
                    ${c.name}
                </li>
            </c:forEach>
        </ul>

        <ul>
            <c:forEach items="${courses}" var="c">
                <li>
                    ${c.name}
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
