<%-- 
    Document   : teachers
    Created on : Aug 5, 2024, 5:04:19 PM
    Author     : Admin
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-primary mt-1">QUẢN LÝ GIÁO VIÊN</h1>

<section>
    <div class="container">
        <c:url value="/teachers" var="action" />
        <form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="teacher">
            <div class="mb-3 mt-3">
                <label for="position" class="form-label">Position:</label>
                <form:input path="position" type="text" class="form-control" id="position" placeholder="type position..." name="position" />
                
            </div>
            <div class="mb-3 mt-3">
                <label for="description" class="form-label">Description: </label>
                <form:input path="description" type="text" class="form-control" id="description" placeholder="type description..." name="description" />
               
            </div>
        </form:form>
    </div>
</section>