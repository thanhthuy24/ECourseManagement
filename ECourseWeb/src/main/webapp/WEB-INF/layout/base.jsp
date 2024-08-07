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
        <style>
            .menu {
                padding: 20px;
                font-size: 20px;
                font-weight: bold;
                margin-left: 5px;
            }
            .list-a a {
                margin-bottom: 10px;
                border: 0px;
                /*                display: block;
                                text-align: center;*/
            }
            .list-group-item {
                display: block;
                text-align: center;
                color: black;
                text-decoration: none;
                padding: 10px;
                transition: background-color 0.002s, color 0.02s;
            }

            .list-group-item.active {
                background-color: #F7DBF0;
                color: black;
            }

            .form-input {
                width: 80%;
            }

            .label-input {
                font-weight: bold;
            }

        </style>
    </head>
    <body style="background: radial-gradient(circle, #F7DBF0, #DFF4F3); width: 100% " >
        <tiles:insertAttribute name="header" />

        <div class="row">
            <tiles:insertAttribute name="left-side" />
            <div class="col-md-9 col-12" style="background: radial-gradient(circle, #F7DBF0, #DFF4F3)">
                <tiles:insertAttribute name="content" />
            </div>
        </div>
    </div>
    <!--class="container"-->
    <tiles:insertAttribute name="footer" />
</body>
</html>