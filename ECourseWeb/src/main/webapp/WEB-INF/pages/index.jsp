<%-- 
    Document   : index
    Created on : Jul 26, 2024, 9:35:27 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <ul>
            <c:forEach items="${cates}" var="c">
                <li>
                    ${c.name}
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
