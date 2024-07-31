<%-- 
    Document   : base
    Created on : Jul 31, 2024, 9:05:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div class="container">
            <tiles:insertAttribute name="content" />
        </div>
        
        <tiles:insertAttribute name="footer" />
    </body>
</html>
