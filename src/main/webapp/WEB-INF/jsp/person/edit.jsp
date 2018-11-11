<%-- 
    Document   : edit
    Created on : Apr 22, 2011, 3:04:46 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">
        <spring:url value="/resources/layout.css" var="layoutCSS" />

        <title>Edit Person</title>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="container">
            <h1>Edit Person</h1>
            <c:if test="${fn:length(errors) gt 0}">
                <p>Please correct the following errors in your submission:</p>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <form action="${pageContext.request.contextPath}/person/edit" method="POST">
                <input type="hidden" name="personId" value="${person.personId}"/>
                <input type="hidden" name="addressId" value="${address.addressId}"/>
                <input type="hidden" name="addressType" value="${address.addressType}"/>
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input id="firstName" class="form-control" type="text" name="firstName" value="${person.firstName}"/>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input id="lastName" class="form-control" type="text" name="lastName" value="${person.lastName}"/>
                </div>
                <div class="form-group">
                    <label for="emailAddress">Email Address:</label>
                    <input id="emailAddress" class="form-control" type="text" name="emailAddress" value="${person.emailAddress}"/>
                </div>
                <div class="form-group">
                    <label for="streetAddress">Street Address:</label>
                    <input id="streetAddress" class="form-control" type="text" name="streetAddress" value="${person.address.streetAddress}"/>
                </div>
                <div class="form-group">
                    <label for="city">City:</label>
                    <input id="city" class="form-control" type="text" name="city" value="${address.city}"/>
                </div>
                <div class="form-group">
                    <label for="state">State:</label>
                    <input id="state" class="form-control" type="text" name="state" value="${address.state}"/>
                </div>
                <div class="form-group">
                    <label for="zipCode">Zip Code:</label>
                    <input id="zipCode" class="form-control" type="text" name="zipCode" value="${address.zipCode}"/>
                </div>
                <div class="form-group">
                    <label for="client">Client:</label>
                    <select id="client" class="form-control selectpicker" data-live-search="true" name="clientId">
                        <option value="-1">None</option>
                        <c:forEach items="${clients}" var="client">
                            <option value="${client.clientId}" ${person.client.clientId == client.clientId ? 'selected="selected"' : ''}>${client.companyName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-primary" type="submit" name="Submit" value="Submit">Submit</button>
                <br/>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
    </body>
</html>
