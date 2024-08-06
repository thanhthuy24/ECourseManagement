<%-- 
    Document   : teacherView
    Created on : Aug 6, 2024, 5:34:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<h1 class="text-center text-primary mt-1">QUẢN LÝ GIÁO VIÊN</h1>

<section class='container'>

    <table class="table">
        <thead>
            <tr>

                <th>Name</th>
                <th>Join date</th>
                <th>Email</th>
                <th>Phone number</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${teachers}" var="c" >
                <tr id="teachers${c.id}">
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
                        <c:url value="/teachers/${c.id}" var="u" />
                        <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

                        <c:url value="/api/teachers/${c.id}" var="endpoint" />
                        <button onClick="deleteTeacher('${endpoint}', ${c.id})" 
                                class="btn btn-danger">&times;</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<!--style="background-color: #EF5A6F;" 
                                class="btn">&#128465;</button>-->