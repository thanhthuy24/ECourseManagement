<%-- 
    Document   : assignments
    Created on : Aug 22, 2024, 12:47:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<Section>
    <h1 class="text-center text-primary mt-1">QUẢN LÝ BÀI TẬP</h1>
    <div >
        <div class="d-flex mt-3" style="justify-content: space-between">
            <div style="display: flex;
                 justify-content: flex-end;
                 margin-right: 7%;">
                <a class="btn mt-2" href="<c:url value="/assignment" />"
                   style="color: #468585;
                   background-color: #D8EFD3;
                   border-color: #D8EFD3;
                   font-weight: bold;" >
                    Add assignment
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
                    <th>Tag</th>
                    <th>Created date</th>
                    <th>Due date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${assignments}" var="c" >
                    <tr id="assignment${c.id}">
                        <td style="width: 5%">
                            ${c.id}
                        </td>
                        <td style="width: 25%; font-weight: bold">
                            ${c.name}
                        </td>
                       
                        <td>
                            ${c.tagId.name} 
                        </td>
                        <td>
                            <fmt:formatDate value="${c.createdDate}" pattern="dd/MM/yyyy" />
                        </td>
                        <td>
                            <fmt:formatDate value="${c.dueDate}" pattern="dd/MM/yyyy" />
                        </td>
                        <td>
                    <c:url value="/assignments/${c.id}" var="u" />
                    <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

                    <c:url value="/api/assignments/${c.id}" var="endpoint" />
                    <button id="btnDelete" onclick="deleteAssignment('${endpoint}', ${c.id})" 
                            class="btn btn-danger">&#128465;</button>
                        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>     
</Section>