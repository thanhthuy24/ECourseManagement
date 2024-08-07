<%-- 
    Document   : base
    Created on : Jul 31, 2024, 9:05:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        
        <script src="<c:url value="/js/script.js" />"></script>
        <script src="<c:url value="/js/courseDelete.js" />"></script>

    </head>
    <body style="background: radial-gradient(circle, #F7DBF0, #DFF4F3); width: 100% " >
        <tiles:insertAttribute name="header" />

        <tiles:insertAttribute name="content" />
    </div>
    <!--class="container"-->
    <tiles:insertAttribute name="footer" />
</body>
</html>
<!--<style>
            .sidebar {
                position: fixed;
                top: 60px; /* Adjust based on header height */
                left: 0;
                width: 200px;
                height: calc(100% - 100px); /* Adjust based on header and footer height */
                background-color: #f8f9fa;
                overflow-y: auto;
                box-shadow: 2px 0 4px rgba(0, 0, 0, 0.1);
                padding: 10px;
            }

            .content {
                margin-top: 60px; /* Adjust based on header height */
                padding: 20px;
                height: calc(100% - 100px); /* Adjust based on header and footer height */
                overflow-y: auto;
            }
        </style>-->