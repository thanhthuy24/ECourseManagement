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
        <!--        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#" style="font-family: inherit">ECourse</a>
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

</nav>-->
        <section>
            <div class="row ">
                <div class="col-md-2 col-12 bg-light " >
                    <c:url value="/" var="action" />
                    <form action="${action}">
                        <div class="mb-3 mt-3 container" >
                            <label for="fromPrice" class="form-label">Từ giá</label>
                            <input type="number" class="form-control" id="fromPrice" placeholder="Từ giá" name="fromPrice">
                        </div>
                        <div class="mb-3 container" >
                            <label for="toPrice" class="form-label">Đến giá</label>
                            <input type="number" class="form-control" id="toPrice" placeholder="Đến giá" name="toPrice">
                        </div>
                        <div class="mb-3 container">
                            <input type="submit" class="btn btn-success" value="Tìm" />
                        </div>

                    </form>
                </div>
                <div class="col-md-7 col-12" style="background: #EEEEEE">
                    <c:url value="/" var="action" />
                    <form action="${action}" class="d-flex justify-content-end mb-3 mt-3 container">
                        <!-- Removed inline style and used Bootstrap classes -->
                        <input
                            style="width: 200px; height: 43px"
                            type="text"
                            class="form-control me-2"
                            id="q"s
                            placeholder="Tên khóa học"
                            name="q"
                            />
                        <input type="submit" class="btn btn-success" value="Tìm" />
                    </form>

                    <div class=" d-flex flex-wrap">
                        <c:forEach items="${courses}" var="c" >
                            <div class="container mt-3" id="course${c.id}" style="width: 280px; margin: 0">
                                <div class="card" style="width:250px">
                                    <img class="card-img-top" src="${c.image}" alt="Card image" style="width:100%">
                                    <div class="card-body">
                                        <h4 class="card-title">${c.name}</h4>
                                        <p class="card-text">${c.description}</p>
                                        <p class="card-text">${String.format("%,d", c.price)}</p>
                                        <div class="row">
                                            <div style="justify-content: space-between">
                                                <a href="#" class="btn btn-primary">See Profile</a>
                                                <a href="#" class="btn btn-primary">See Profile</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-3 col-12 bg-light" >
                    <div>
                        <ul style="list-style-type: none;">

                            <c:forEach items="${cates}" var="c">
                                <c:url value="/" var="cateURL">
                                    <c:param name="cateId" value="${c.id}"/>
                                </c:url>
                                <li style="width: 200px ;padding: 10px; background: white; margin: 10px; border: 1px #EEEEEE solid; display: flex; justify-content: center; align-items: center;">
                                    <a style="text-decoration: none; " href="${cateURL}">${c.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
        </section>

        <!--        <h1>Hello World!</h1>
        
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
    </ul>-->
    </body>
</html>
