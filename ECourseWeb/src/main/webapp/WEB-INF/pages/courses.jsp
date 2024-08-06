<%-- 
    Document   : course
    Created on : Aug 5, 2024, 2:48:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-primary mt-1">QUẢN LÝ KHÓA HỌC</h1>

<section>
    <div class="row ">
        <div class="col-md-2 col-12 bg-light " >
            3
        </div>
        <div class="col-md-9 col-12 bg-light " >
            <c:url value="/courses" var="action" />
            <form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="course">
                <div class="mb-3 mt-3">
                    <label for="name" class="form-label">Course name:</label>
                    <form:input path="name" type="text" class="form-control" id="name" placeholder="type your course name..." name="name" />
                    <form:errors path="name" element="div" cssClass="alert alert-danger" />
                </div>
                <div class="mb-3 mt-3">
                    <label for="description" class="form-label">Course description:</label>
                    <form:input path="description" type="text" class="form-control" id="description" placeholder="type description..." name="description" />
                </div>

                <div class="mb-3 mt-3">
                    <label for="browser" class="form-label">Danh mục: </label>
                    <form:select class="form-select" path="categoryId" >
                        <c:forEach items="${categories}" var="c">
                            <c:choose>
                                <c:when test="${c.id == course.categoryId.id}">
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
                    <label for="browser" class="form-label">Instructor: </label>
                    <form:select class="form-select" path="teacherId" >
                        <c:forEach items="${teachers}" var="c">
                            <c:choose>
                                <c:when test="${c.id == teacher.userId}">
                                    <option value="${c.id}" selected>Giáo sư ${c.userId.lastName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.id}">Giáo sư ${c.userId.lastName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="mb-3 mt-3">
                    <label for="price" class="form-label">Pricing:</label>
                    <form:input path="price" type="number" class="form-control" id="price" name="price" />
                </div>

                <div class="mb-3 mt-3">
                    <label for="discount" class="form-label">Discount:</label>
                    <form:input path="discount" type="number" class="form-control" id="discount" name="discount" />
                </div>

                <div class="mb-3 mt-3">
                    <label for="browser" class="form-label">Tag:</label>
                    <form:select class="form-select" path="tagId" >
                        <c:forEach items="${tags}" var="c">
                            <c:choose>
                                <c:when test="${c.id == tag.name}">
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
                    <label for="file" class="form-label">Ảnh sản phẩm:</label>
                    <form:input path="file" type="file" accept=".jpg,.png" class="form-control" id="file" name="file" />
                    <c:if test="${course.image != null}">
                        <img class="mt-1" src="${course.image}" alt="${course.image}" width="120px" />
                    </c:if>
                </div>

                <div class="mb-3 mt-3">
                    <form:hidden path="id" />
                    <form:hidden path="image" />
                    <button class="btn btn-success" type="submit">

                        <c:choose>
                            <c:when test="${course.id != null}">
                                <option value="${c.id}" selected>Update course</option>
                            </c:when>
                            <c:otherwise>
                                Add course
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</section>

