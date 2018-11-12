<%--
    Document   : delete
    Created on : Apr 22, 2011, 3:55:55 PM
    Author     : FMilens
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <spring:url value="/resources/css/layout.css" var="layoutCSS" />
        <link rel="stylesheet" href="${layoutCSS}">

        <title>Delete Person</title>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="container">
            <h1>Delete Person</h1>
            <p>You are about to delete the person ${person.firstName} ${person.lastName}:  Are you sure?</p>
            <form action="${pageContext.request.contextPath}/person/delete" method="post">
                <input type="hidden" name="personId" value="${person.personId}"/>
                <input type="hidden" name="addressId" value="${address.addressId}"/>
                <button type="submit" class="btn btn-secondary" name="command" value="Cancel">Cancel</button>
                <button type="submit" class="btn btn-danger" name="command" value="Delete">Delete</button>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
