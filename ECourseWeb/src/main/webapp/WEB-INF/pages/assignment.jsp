<%-- 
    Document   : assignment
    Created on : Aug 22, 2024, 1:03:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section>
    <div>
        <h1 class="text-center text-primary mt-1">THÊM BÀI TẬP</h1>

        <c:url value="/assignments" var="action" />

        <form:form method="post" enctype="multipart/form-data" action="${action}" 
                   modelAttribute="assignment">
            <div class="mb-3 mt-3">
                <label for="name" class="form-label label-input">Assignment name:</label>
                <form:input path="name" type="text" class="form-control form-input" id="name" placeholder="type lesson name..." name="name" />
                <form:errors path="name" element="div" cssClass="alert alert-danger" />
            </div>

            <div class="mb-3 mt-3">
                <label for="dueDate" class="form-label label-input">Due date: </label>
                <form:input type="date" path="dueDate" id="dueDate" name="dueDate" class="form-control form-input" />
                <form:errors path="dueDate" cssClass="text-danger" />
            </div>

            <div class="mb-3 mt-3">
                <label for="tagId" class="form-label label-input">Tag: </label>
                <form:select id="tagId" class="form-select form-input" path="tagId" >
                    <c:forEach items="${tags}" var="c">
                        <c:if test="${c.id == 4 || c.id == 5}">
                            <c:choose>
                                <c:when test="${c.id == assignment.tagId.id}">
                                    <option value="${c.id}" selected>${c.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.id}">${c.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3 mt-3">
                <label for="lessonId" class="form-label label-input">Lesson: </label>
                <form:select id="lessonId" class="form-select form-input" path="lessonId" >
                    <c:forEach items="${lessons}" var="c">
                        <c:choose>
                            <c:when test="${c.id == assignment.lessonId.id}">
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
                <label for="courseId" class="form-label label-input">Course: </label>
                <form:select id="courseId" class="form-select form-input" path="courseId" >
                    <c:forEach items="${courses}" var="c">
                        <c:choose>
                            <c:when test="${c.id == assignment.courseId.id}">
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
                        <c:when test="${assignment.id != null}">
                            Update assignment
                        </c:when>
                        <c:otherwise>
                            Add assignment
                        </c:otherwise>
                    </c:choose>
                </button>
            </div>
        </form:form>
    </div>
</div>
</section>