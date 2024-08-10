<%-- 
    Document   : lessons
    Created on : Aug 10, 2024, 8:37:15 PM
    Author     : Admin
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <h1 class="text-center text-primary mt-1">QUẢN LÝ BÀI HỌC</h1>
    <div >
        <div class="d-flex mt-3" style="justify-content: space-between">
            <div style="display: flex;
                 justify-content: flex-end;
                 margin-right: 7%;">
                <a class="btn mt-2" href="<c:url value="/lesson" />"
                   style="color: #468585;
                   background-color: #D8EFD3;
                   border-color: #D8EFD3;
                   font-weight: bold;" >
                    Add lesson
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
                    <th>Created date</th>
                    <th>Description</th>
                    <th>Course</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lessons}" var="c" >
                    <tr id="lesson${c.id}">
                        <td style="width: 5%">
                            ${c.id}
                        </td>
                        <td style="width: 25%; font-weight: bold">
                            ${c.name}
                        </td>
                        <td>
                            <fmt:formatDate value="${c.createdDate}" pattern="dd/MM/yyyy" />
                        </td>

                        <td style="width: 25%">${c.description}</td>
                        <td>
                            ${c.courseId.name} 
                        </td>
                        <td>
                            <c:url value="/lessons/${c.id}" var="u" />
                            <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

                            <c:url value="/api/lessons/${c.id}" var="endpoint" />
                            <button id="btnDelete" onclick="deleteLesson('${endpoint}', ${c.id})" 
                                    class="btn btn-danger">&#128465;</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>     
</section>
