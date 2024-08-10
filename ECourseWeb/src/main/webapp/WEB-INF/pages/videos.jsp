<%-- 
    Document   : videos
    Created on : Aug 9, 2024, 4:17:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section>
    <div >
        <div class="d-flex mt-3" style="justify-content: space-between">
            <div style="display: flex;
                 justify-content: flex-end;
                 margin-right: 7%;">
                <a class="btn mt-2" href="<c:url value="/videosCreate" />"
                   style="color: #468585;
                   background-color: #D8EFD3;
                   border-color: #D8EFD3;
                   font-weight: bold;" >
                    Thêm video
                </a>
            </div>   
        </div>

    </div>

    <div>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Lesson</th>
                    <th>Course</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${videos}" var="c" >
                    <tr id="video${c.id}">
                        <td style="width: 5%">
                            ${c.id}
                        </td>
                        <td style="width: 15%; font-weight: bold">
                            ${c.name}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${c.description != ''}">
                                    <video width="350" height="260" controls>
                                        <source src="${c.description}" type="video/mp4">
                                        Trình duyệt của bạn không hỗ trợ thẻ video.
                                    </video>
                                </c:when>
                                <c:otherwise>
                                    Không có video
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="width: 15%">
                            ${c.lessonId.name} 
                        </td>
                        <td>
                            ${c.courseId.name} 
                        </td>
                        <td>
                            <c:url value="/videos/${c.id}" var="u" />
                            <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

                            <c:url value="/api/videos/${c.id}" var="endpoint" />
                            <button id="btnDelete" onclick="deleteVideo('${endpoint}', ${c.id})" 
                                    class="btn btn-danger">&#128465;</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </div>     
</div>
</section>
