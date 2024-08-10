<%-- 
    Document   : teachers
    Created on : Aug 10, 2024, 8:02:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<section>
    <div >

        <h1 class="text-center text-primary mt-1">QUẢN LÝ GIÁO VIÊN</h1>
        <div >
            <div class="d-flex mt-3" style="justify-content: space-between">
                <div style="display: flex;
                     justify-content: flex-end;
                     margin-right: 7%;">
                    <a class="btn mt-2" href="<c:url value="/teacherCreate" />"
                       style="color: #468585;
                       background-color: #D8EFD3;
                       border-color: #D8EFD3;
                       font-weight: bold;" >
                        Add teacher
                    </a>
                </div>   
            </div>

        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Join date</th>
                    <th>Position</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${teachers}" var="c" >
                    <tr id="teacher${c.id}">
                        <td>
                            <img class="rounded-circle" src="${c.userId.avatar}" alt="Card image" style="width:40px;" />
                            ${c.userId.firstName} ${c.userId.lastName} 
                        </td>
                        <td>
                            <fmt:formatDate value="${c.userId.createdDate}" pattern="dd/MM/yyyy" />
                        </td>
                        <td>${c.position}</td>
                        <td style="width: 28%">${c.description}</td>
                        <td>
                            <c:choose>
                                <c:when test="${c.userId.isActive}">
                                    <button class="btn btn-success">Còn hoạt động</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-danger">Ngưng hoạt động</button>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:url value="/teachers/${c.id}" var="u" />
                            <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

                            <c:url value="/api/teachers/${c.id}" var="endpoint" />
                            <button onClick="deleteTeacher('${endpoint}', ${c.id})" 
                                    class="btn btn-danger">&#128465;</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</section>

