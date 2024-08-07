<%-- 
    Document   : index
    Created on : Jul 26, 2024, 9:35:27 PM
    Author     : Admin
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                        <a href="<c:url value="/" />" class="list-group-item">Courses</a>
                        <a href="<c:url value="/teachers" />" class="list-group-item">Teachers</a>
                        <a href="#" class="list-group-item">Students</a>
                        <a href="#" class="list-group-item">Resource</a>
                        <a href="#" class="list-group-item">Certificate</a>
                    </div>

                </div>
                <div class="col-md-9 col-12" <div style="background: radial-gradient(circle, #F7DBF0, #DFF4F3)">

                    <div class="d-flex mt-3" style="justify-content: space-between">
                        <div style="margin-top: 20px">
                            <c:url value="/" var="action" />
                            <form action="${action}" class="d-flex">
                                <div style="display: flex; justify-content: space-evenly; height: 37px;">
                                    <label for="fromPrice" class="form-label" style="width: 80px; align-self: center">Từ giá</label>
                                    <input type="number" class="form-control" id="fromPrice" placeholder="Từ giá" name="fromPrice">
                                </div>
                                <div style="display: flex; justify-content: space-evenly; height: 37px;">
                                    <label for="toPrice" class="form-label" style="width: 80px; margin-left: 5px; align-self: center">Đến giá</label>
                                    <input type="number" class="form-control" id="toPrice" placeholder="Đến giá" name="toPrice">
                                </div>
                                <div >
                                    <input type="submit" class="btn btn-success" value="Tìm" />
                                </div>
                            </form>
                        </div>
                        <div>
                            <c:url value="/" var="action" />
                            <form  action="${action}" class="d-flex justify-content-end mb-3 mt-3 container">
                                <!-- Removed inline style and used Bootstrap classes -->
                                <div class="d-flex">
                                    <input
                                        style="width: 200px; height: 43px"
                                        type="text"
                                        class="form-control me-2"
                                        id="q"s
                                        placeholder="Tên khóa học"
                                        name="q"
                                        />
                                    <div>
                                        <input type="submit" class="btn btn-success" value="Tìm" />
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                    <div style="display: flex;
                         justify-content: flex-end;
                         margin-right: 7%;">
                        <a class="btn mt-2" href="<c:url value="/courses" />"
                           style="color: #468585;
                           background-color: #D8EFD3;
                           border-color: #D8EFD3;
                           font-weight: bold;" >
                            Thêm khóa học
                        </a>
                    </div>   
                    <div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Created date</th>
                                    <th>Tag</th>
                                    <th>Price</th>
                                    <th>Instructor</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${courses}" var="c" >
                                    <tr id="course${c.id}">
                                        <td>
                                            <img src="${c.image}" alt="Card image" style="width:60px;" />
                                            ${c.name}
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${c.createdDate}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td>
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

                                        </td>
                                        <td>${c.price}</td>
                                        <td>
                                            ${c.teacherId.userId.firstName} ${c.teacherId.userId.lastName} 
                                        </td>
                                        <td>
                                            <c:url value="/courses/${c.id}" var="u" />
                                            <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

                                            <!--                                    <c:url value="/api/courses/${c.id}" var="endpoint" />
                                            -->                                    <button id="btnDelete" onClick="deleteCourse('${endpoint}', ${c.id})" 
                                                                                           class="btn btn-danger">&#128465;</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>     
                </div>
        </section>

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
