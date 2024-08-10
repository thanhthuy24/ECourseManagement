<%-- 
    Document   : users
    Created on : Aug 10, 2024, 4:30:58 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<section>
    <div >

        <h1 class="text-center text-primary mt-1">USER INFORMATION</h1>
        <div >
            <div class="d-flex mt-3" style="justify-content: space-between">
                <div style="display: flex;
                     justify-content: flex-end;
                     margin-right: 7%;">
                    <a class="btn mt-2" href="<c:url value="/user" />"
                       style="color: #468585;
                       background-color: #D8EFD3;
                       border-color: #D8EFD3;
                       font-weight: bold;" >
                        Thêm user
                    </a>
                </div>   
            </div>

        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Join date</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="c" >
                    <tr id="user${c.id}">
                        <td>
                            <img src="${c.avatar}" alt="Card image" style="width:40px;" />
                            ${c.firstName} ${c.lastName} 
                        </td>
                        <td>${c.username}</td>
                        <td>
                            <fmt:formatDate value="${c.createdDate}" pattern="dd/MM/yyyy" />
                        </td>
                        <td>${c.email}</td>
                        <td>${c.phoneNumber}</td>
                        <td>
                            ${c.role}
                        </td>

                        <td>
                            <c:url value="/users/${c.id}" var="u" />
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
