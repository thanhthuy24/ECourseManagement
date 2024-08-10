<%-- 
    Document   : teacher
    Created on : Aug 10, 2024, 8:02:53 PM
    Author     : Admin
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section>
    <div>
        <h1 class="text-center text-primary mt-1">UPDATE USER'S INFORMATION</h1>
        <c:url value="/teachers" var="action" />

        <form:form method="post" enctype="multipart/form-data" action="${action}" 
                   modelAttribute="teacher">
            <div>
                <div class="mb-3 mt-3">
                    <label for="position" class="form-label label-input">Position: </label>
                    <form:input path="position" type="text" class="form-control form-input" id="position" name="position" />
                </div>

                <div class="mb-3 mt-3">
                    <label for="description" class="form-label label-input">Description: </label>
                    <form:input path="description" type="text" class="form-control form-input" id="description" name="description" />
                </div>

                <div class="mb-3 mt-3">
                    <label for="userId" class="form-label label-input">Username: </label>
                    <form:select id="userId" class="form-select form-input" path="userId" >
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
            </div>
            <div class="mb-3 mt-3">
                <form:hidden path="id" />
                <button class="btn btn-success" type="submit">
                    Update information
                </button>
            </div>
        </form:form>



    </div>
</section>
