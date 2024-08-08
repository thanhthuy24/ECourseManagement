<%-- 
    Document   : lessons
    Created on : Aug 7, 2024, 4:48:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<div>
    <table class="table">
        <thead>
            <tr>
                <th>Name</th>
                <th>Created date</th>
                <th>Tag</th>
                <th>Price</th>
                <th>Instructor</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
<c:forEach items="${courses}" var="c" >
    <tr id="course${c.id}">
        <td style="width: 40%">
            <img src="${c.image}" alt="Card image" style="width:60px;" />
    ${c.name}
</td>
<td>
<fmt:formatDate value="${c.createdDate}" pattern="dd/MM/yyyy" />
</td>
<td>
    <c:choose>
        <c:when test="${c.tagId.name == 'Beginner'}">
            <button type="button" class="btn mb-2" style="color: #468585; background-color: #D8EFD3; border-color: #D8EFD3; font-weight: bold;" >
            ${c.tagId.name}
        </button>
        </c:when>
        <c:when test="${c.tagId.name == 'Intermediate'}">
            <button type="button" class="btn mb-2" style="color: #FFA823; background-color: #FFFED3; border-color: #FFFED3; font-weight: bold;" >
            ${c.tagId.name}
        </button>
        </c:when>
        <c:otherwise>
            <button type="button" class="btn mb-2" style="color: #C63C51; background-color: #FFAAAA; border-color: #FFAAAA; font-weight: bold" >
            ${c.tagId.name}
        </button>
        </c:otherwise>
    </c:choose>

</td>
<td>${c.price}</td>
<td>
    ${c.teacherId.userId.firstName} ${c.teacherId.userId.lastName} 
</td>
<td>
    <c:url value="/courses/${c.id}" var="u" />
    <a href="${u}" class="btn" style="background-color: #597445">&#128194;</a>

    <c:url value="/courses/${c.id}" var="u" />
    <a href="${u}" class="btn" style="background-color: #B762C1">&#128221;</a>

    <c:url value="/api/courses/${c.id}" var="endpoint" />
    <button id="btnDelete" onClick="deleteCourse('${endpoint}', ${c.id})" 
            class="btn btn-danger">&#128465;</button>
</td>
</tr>
</c:forEach>
</tbody>
</table>

</div>     -->

<section>
    <h1 class="text-center text-primary mt-1">QUẢN LÝ BÀI HỌC</h1>
    <c:forEach items="${lessons}" var="c">
        <div id="lessonWrapper${c.id}">
            <div class="card">
                <div class="card-header">
                    <a class="btn" data-toggle="#lessonContent${c.id}">
                        ${c.name}
                    </a>
                </div>
                <div id="lessonContent${c.id}" class="collapse" data-bs-parent="#lessonWrapper${c.id}">
                    <div class="card-body">
                        ${c.description}
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</section>

<style>
    .hidden {
        display: none;
    }
</style>
<script>
    // Hàm để ẩn/hiện phần tử
    function toggleContent(targetId) {
        var content = document.querySelector(targetId);
        
        if (content.classList.contains('show')) {
            content.classList.remove('show');
            content.classList.add('collapse');
        } else {
            content.classList.remove('collapse');
            content.classList.add('show');
        }
    }

    // Thêm sự kiện click cho tất cả các liên kết với thuộc tính data-toggle
    document.querySelectorAll('[data-toggle]').forEach(function(element) {
        element.addEventListener('click', function() {
            var targetId = this.getAttribute('data-toggle');
            toggleContent(targetId);
        });
    });
</script>
