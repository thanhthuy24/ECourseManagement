<%-- 
    Document   : teachers
    Created on : Aug 5, 2024, 5:04:19 PM
    Author     : Admin
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<section>
    <div>
        <h1 class="text-center text-primary mt-1">QUẢN LÝ GIÁO VIÊN</h1>
        <c:url value="/teachers" var="action" />

        <form:form method="post" enctype="multipart/form-data" action="${action}" 
                   modelAttribute="user">
            <div class="d-flex">
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="firstName" class="form-label">First name: </label>
                    <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="type position..." name="firstName" />
                </div>
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="lastName" class="form-label">Last name: </label>
                    <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="type description..." name="lastName" />
                </div>
                <div class="mb-3 mt-3" style="margin: 5px">
                    <label for="email" class="form-label">Email: </label>
                    <form:input path="email" type="email" class="form-control" id="email" placeholder="type description..." name="email" />
                </div>
            </div>
        </form:form>
        <form:form method="post" enctype="multipart/form-data" action="${action}" 
                   modelAttribute="teacher">
            <div class="mb-3 mt-3">
                <label class="form-label">First name: </label>
            </div>
            <div class="mb-3 mt-3">
                <label for="position" class="form-label">Position:</label>
                <form:input path="position" type="text" class="form-control" id="position" placeholder="type position..." name="position" />
            </div>
            <div class="mb-3 mt-3">
                <label for="description" class="form-label">Description: </label>
                <form:input path="description" type="text" class="form-control" id="description" placeholder="type description..." name="description" />
            </div>
            <div class="mb-3 mt-3">
                <label for="browser" class="form-label">User id: </label>
                <form:select class="form-select" path="userId" >
                    <c:forEach items="${users}" var="c">
                        <c:choose>
                            <c:when test="${c.id == teacher.userId.id}">
                                <option value="${c.id}" selected>${c.username}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${c.id}">${c.username}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </div>
            <div class="mb-3 mt-3">
                <form:hidden path="id" />
                <button class="btn btn-success" type="submit">
                    <c:choose>
                        <c:when test="${teacher.id != null}">
                            <option value="${c.id}" selected>Update information</option>
                        </c:when>
                        <c:otherwise>
                            Add course
                        </c:otherwise>
                    </c:choose>
                </button>
            </div>
        </form:form>


    </div>
</section>