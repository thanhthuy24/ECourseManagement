<%-- 
    Document   : sidebar
    Created on : Aug 7, 2024, 4:44:08 PM
    Author     : Admin
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-2 col-12" style="background-color: white">
    <div class="menu">
        <p>Menu</p>
    </div>
    <div class="list-group list-a">
        <a href="<c:url value='/' />" class="list-group-item">Courses</a>
        <a href="<c:url value='/teachers' />" class="list-group-item">Teachers</a>
        <a href="<c:url value='/videos' />" class="list-group-item">Videos</a>
        <a href="<c:url value='/lessons' />" class="list-group-item">Lessons</a>
        <a href="<c:url value='/users' />" class="list-group-item">Users</a>

        <div class="list-group list-a">
            <spring:authorize access="!isAuthenticated()">
                <a class="list-group-item" href="<c:url value="/login" />">
                    Login
                </a>
            </spring:authorize>
            <spring:authorize access="isAuthenticated()">
                <a class="list-group-item" href="<c:url value="/" />">
                    Xin chào <spring:authentication property="principal.username" />
                </a>
                <a class="btn btn-danger" href="<c:url value="/logout" />">
                    Đăng xuất
                </a>
            </spring:authorize>
        </div>
    </div>


</div>

