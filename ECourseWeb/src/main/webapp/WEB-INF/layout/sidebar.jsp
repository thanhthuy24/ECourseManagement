<%-- 
    Document   : sidebar
    Created on : Aug 7, 2024, 4:44:08 PM
    Author     : Admin
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-2 col-12" style="background-color: white">
    <div class="menu">
        <p>Menu</p>
    </div>
    <div class="list-group list-a">
        <a href="<c:url value='/' />" class="list-group-item">Courses</a>
        <a href="<c:url value='/teachers' />" class="list-group-item">Teachers</a>
        <a href="<c:url value='/lessons' />" class="list-group-item">Lessons</a>
        <a href="#" class="list-group-item">Resource</a>
        <a href="#" class="list-group-item">Certificate</a>
    </div>
</div>

