<%-- 
    Document   : coursesTable
    Created on : Aug 7, 2024, 11:30:48 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section>
    <div>
        <h1 class="text-center text-primary mt-1">QUẢN LÝ KHÓA HỌC</h1>
        <c:url value="/courses" var="action" />
        <form:form method="post" enctype="multipart/form-data" 
                   style="margin-left: 20%" action="${action}" modelAttribute="course">
            <div class="mb-3 mt-3">
                <label for="name" class="form-label label-input">Course name:</label>
                <form:input path="name" type="text" class="form-control form-input" id="name" placeholder="type your course name..." name="name" />
                <form:errors path="name" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="description" class="form-label label-input">Course description:</label>
                <form:input path="description" type="text" class="form-control form-input"  id="description" placeholder="type description..." name="description" />
            </div>

            <div class="mb-3 mt-3">
                <label for="browser" class="form-label label-input">Danh mục: </label>
                <form:select class="form-select form-input" path="categoryId" >
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
                <label for="browser" class="form-label label-input">Instructor: </label>
                <form:select class="form-select form-input" path="teacherId" >
                    <c:forEach items="${teachers}" var="c">
                        <c:choose>
                            <c:when test="${c.id == course.teacherId.id}">
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
                <label for="price" class="form-label label-input">Pricing:</label>
                <form:input path="price" type="number" class="form-control form-input" id="price" name="price" />
            </div>

            <div class="mb-3 mt-3">
                <label for="discount" class="form-label label-input">Discount:</label>
                <form:input path="discount" type="number" class="form-control form-input" id="discount" name="discount" />
            </div>

            <div class="mb-3 mt-3">
                <label for="browser" class="form-label label-input">Tag:</label>
                <form:select class="form-select form-input" path="tagId" >
                    <c:forEach items="${tags}" var="c">
                        <c:choose>
                            <c:when test="${c.id == course.tagId.id}">
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
                <label for="file" class="form-label label-input">Ảnh sản phẩm:</label>
                <form:input path="file" type="file" accept="*" 
                            class="form-control form-input" id="file" name="file" />
                <c:if test="${course.image != null}">
                    <img class="mt-3" src="${course.image}" alt="${course.image}" width="120px" />
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

