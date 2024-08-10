<%-- 
    Document   : userCreate
    Created on : Aug 10, 2024, 4:06:22 PM
    Author     : Admin
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<section>
    <div>
        <h1 class="text-center text-primary mt-1">UPDATE USER'S INFORMATION</h1>
        <c:url value="/users" var="action" />

        <form:form method="post" enctype="multipart/form-data" action="${action}" 
                   modelAttribute="user">
            <div class="d-flex">
                <div class="mb-3 mt-3">
                    <label for="username" class="form-label label-input">Username:</label>
                    <form:input path="username" type="text" class="form-control form-input" id="username" name="username" />
                </div>
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="firstName" class="form-label">First name: </label>
                    <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="type position..." name="firstName" />
                </div>
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="lastName" class="form-label">Last name: </label>
                    <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="type description..." name="lastName" />
                </div>
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="role" class="form-label">ROLE: </label>
                    <form:input path="role" type="text" class="form-control" id="role" name="role" />
                </div>
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="email" class="form-label">Email: </label>
                    <form:input path="email" type="email" class="form-control" id="email" placeholder="type description..." name="email" />
                </div>
                <div class="mb-3 mt-3">
                    <label for="phoneNumber" class="form-label label-input">Phone number: </label>
                    <form:input path="phoneNumber" type="text" class="form-control form-input" id="phoneNumber" name="phoneNumber" />
                </div>
            </div>
            <div class="mb-3 mt-3">
                <label for="file" class="form-label label-input">Avatar:</label>
                <form:input path="file" type="file" accept="*" 
                            class="form-control form-input" id="file" name="file" />
                <c:if test="${user.avatar != null}">
                    <img class="mt-3" src="${user.avatar}" alt="${user.avatar}" width="120px" />
                </c:if>
            </div>
            <div class="mb-3 mt-3">
                <form:hidden path="id" />
                <form:hidden path="avatar" />
                <button class="btn btn-success" type="submit">
                    Update information
                </button>
            </div>
        </form:form>



    </div>
</section>