<%-- 
    Document   : lesson
    Created on : Aug 10, 2024, 8:54:08 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section>
    <div>
        <h1 class="text-center text-primary mt-1">QUẢN LÝ BÀI HỌC</h1>

        <c:url value="/lessons" var="action" />

        <form:form method="post" enctype="multipart/form-data" action="${action}" 
                   modelAttribute="lesson">
            <div class="mb-3 mt-3">
                <label for="name" class="form-label label-input">Lesson name:</label>
                <form:input path="name" type="text" class="form-control form-input" id="name" placeholder="type lesson name..." name="name" />
            </div>
            <div class="mb-3 mt-3">
                <label for="description" class="form-label label-input">Lesson description:</label>
                <form:input path="description" type="text" class="form-control form-input"  id="description" placeholder="type description..." name="description" />
            </div>

            <div class="mb-3 mt-3">
                <label for="courseId" class="form-label label-input">Course: </label>
                <form:select id="courseId" class="form-select form-input" path="courseId" >
                    <c:forEach items="${courses}" var="c">
                        <c:choose>
                            <c:when test="${c.id == lesson.courseId.id}">
                                <option value="${c.id}" selected>${c.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${c.id}">${c.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3 mt-3">
                <form:hidden path="id" />
                <button class="btn btn-success" type="submit">
                    <c:choose>
                        <c:when test="${lesson.id != null}">
                            Update lesson
                        </c:when>
                        <c:otherwise>
                            Add lesson
                        </c:otherwise>
                    </c:choose>
                </button>
            </div>
        </form:form>
    </div>
</div>
</section>
