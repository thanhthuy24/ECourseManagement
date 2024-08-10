<%-- 
    Document   : video
    Created on : Aug 9, 2024, 4:37:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section>
    <div>
        <h1 class="text-center text-primary mt-1">QUẢN LÝ VIDEO KHÓA HỌC</h1>
       <c:url value="/videos/${videoId}" var="action"/>
        <form:form method="post" enctype="multipart/form-data" 
                   style="margin-left: 20%" action="${action}" modelAttribute="video">
            <div class="mb-3 mt-3">
                <label for="name" class="form-label label-input">Video name:</label>
                <form:input path="name" type="text" class="form-control form-input" id="name" 
                            placeholder="type your course name..." name="name" />
            </div>
            <div class="mb-3 mt-3">
                <label for="file" class="form-label label-input">Upload video:</label>

                <form:input path="file" type="file" accept="*" 
                            class="form-control form-input" id="file" name="file" />
                <c:if test="${video.description != null}">
                    <img class="mt-3" src="${video.description}" alt="${video.description}" width="120px" />
                    <video width="640" height="360" controls>
                        <source src="${video.description}" type="video/mp4">
                    </video>
                </c:if>
            </div>

            <div class="mb-3 mt-3">
                <label for="browser" class="form-label label-input">Lesson: </label>
                <form:select class="form-select form-input" path="lessonId" >
                    <c:forEach items="${lessons}" var="c">
                        <c:choose>
                            <c:when test="${c.id == video.lessonId.id}">
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
                            <c:when test="${c.id == video.courseId.id}">
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
                        <c:when test="${video.id != null}">
                            <option value="${video.id}" selected>Update course</option>
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


