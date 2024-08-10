<%-- 
    Document   : teacherView
    Created on : Aug 6, 2024, 5:34:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<section>
    <div >

        <h1 class="text-center text-primary mt-1">QUẢN LÝ GIÁO VIÊN</h1>

        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Join date</th>
                    <th>Email</th>
                    <th>Phone number</th>
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
                        <td>${c.userId.email}</td>
                        <td>${c.userId.phoneNumber}</td>
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