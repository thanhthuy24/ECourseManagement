<%-- 
    Document   : login
    Created on : Aug 11, 2024, 9:39:04 AM
    Author     : Admin
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%--<spring:url value="/login" var="action" />--%>
<!--<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 style=" font-weight: bold;"><spring:message code="user.login" /></h3>
                </div>
                <div class="card-body">
                    <form action="${action}" method="post">
                        <div class="form-group">
                            <label for="usernameId" style="
                                   font-weight: bold;
                                   margin-bottom: 12px;">
                                <spring:message code="user.username" />
                            </label>
                            <input name="username" id="usernameId" class="form-control" required />
                        </div>
                        <div class="form-group">
                            <label for="passwordId" style="
                                   margin: 12px 0px;
                                   font-weight: bold;">
                                <spring:message code="user.password" />
                            </label>
                            <input id="passwordId" name="password" class="form-control" type="password" required />
                        </div>
                        <div class="form-group text-center" style=" margin-top: 17px;">
                            <input type="submit" class="btn btn-primary" value="<spring:message code="user.login" />"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>-->
<!--<form action="${action}" method="post" >
<div class="form-group">
    <label for="usernameId">
<spring:message code="user.username" />
</label>
<input name="username" id="usernameId"
   class="form-control" />
</div>
<div class="form-group">
<label for="passwordId">
<spring:message code="user.password" />
</label>
<input id="passwordId" name="password"
   class="form-control" type="password" />
</div>
<div class="form-group">
<input type="submit"
   value="<spring:message code="user.login" />"/>
</div>
</form>-->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
