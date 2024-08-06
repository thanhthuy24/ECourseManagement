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
<!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>-->

        <style>
            .menu {
                padding: 20px;
                font-size: 20px;
                font-weight: bold;
                margin-left: 5px;
            }
            .list-a a {
                margin-bottom: 10px;
                border: 0px;
                /*                display: block;
                                text-align: center;*/
            }
            .list-group-item {
                display: block;
                text-align: center;
                color: black;
                text-decoration: none;
                padding: 10px;
                transition: background-color 0.002s, color 0.02s;
            }

            .list-group-item.active {
                background-color: #F7DBF0;
                color: black;
            }
        </style>
    </head>
    <body>
       
        <section>
            <div class="row">
                <div class="col-md-2 col-12 " style="background-color: white" >
                    <div class="menu">
                        <p>Menu</p>
                    </div>
                    <%--<c:url value="/" var="action" />--%>
                    <!--<form  action="${action}" class="d-flex justify-content-end mb-3 mt-3 container">-->
                        <div class="list-group list-a">
                            <a href="<c:url value="/courses" />" class="list-group-item">Courses</a>
                            <a href="<c:url value="/teachers" />" class="list-group-item">Teachers</a>
                            <a href="#" class="list-group-item">Students</a>
                            <a href="#" class="list-group-item">Resource</a>
                            <a href="#" class="list-group-item">Certificate</a>
                        </div>
                    <!--</form>-->
                    <%--<c:url value="/" var="action" />--%>
<!--                    <form action="${action}">
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

                    </form>-->
                </div>
                <div class="col-md-9 col-12" <div style="background: radial-gradient(circle, #F7DBF0, #DFF4F3)">
                    <a class="btn mt-2" href="<c:url value="/courses" />"
                       style="color: #468585;
                       background-color: #D8EFD3;
                       border-color: #D8EFD3;
                       font-weight: bold;" >
                        Thêm khóa học
                    </a>

                    <c:url value="/" var="action" />
                    <form  action="${action}" class="d-flex justify-content-end mb-3 mt-3 container">
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

                    <div class=" d-flex flex-wrap" >

                        <c:forEach items="${courses}" var="c" >
                            <div class="container mt-3" id="course${c.id}" style="width: 280px; margin: 0">
                                <div class="card" style="width:250px;">
                                    <img class="card-img-top" src="${c.image}" alt="Card image" style="width:100%; height: 100%">
                                    <div class="card-body">
                                        <h4 class="card-title">${c.name}</h4>
                                        <p class="card-text">${c.description}</p>

                                        <c:choose>
                                            <c:when test="${c.tagId.name == 'Beginner'}">
                                                <button type="button" class="btn mb-2" style="color: #468585; background-color: #D8EFD3; border-color: #D8EFD3; font-weight: bold;" >
                                                    ${c.tagId.name}
                                                </button>
                                            </c:when>
                                            <c:when test="${c.tagId.name == 'Intermediate'}">
                                                <button type="button" class="btn mb-2" style="color: #FFA823; background-color: #FFFED3; border-color: #FFFED3; font-weight: bold;" >
                                                    ${c.tagId.name}
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn mb-2" style="color: #C63C51; background-color: #FFAAAA; border-color: #FFAAAA; font-weight: bold" >
                                                    ${c.tagId.name}
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                        <p class="card-text">${String.format("%,d", c.price)}VNĐ</p>
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
                <!--                <div class="col-md-3 col-12 bg-light" >
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
    </div>-->
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
        <script>
            // Get all list items
            const listItems = document.querySelectorAll('.list-group-item');

            // Add click event listener to each item
            listItems.forEach(item => {
                item.addEventListener('click', function () {
                    // Remove active class from all items
                    listItems.forEach(item => item.classList.remove('active'));

                    // Add active class to the clicked item
                    this.classList.add('active');
                });
            });
        </script>
    </body>
</html>
