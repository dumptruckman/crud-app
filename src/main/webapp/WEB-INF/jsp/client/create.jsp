<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">
        <spring:url value="/resources/layout.css" var="layoutCSS" />
    
        <title>Create Client</title>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="container">
            <h1>Create Client</h1>
            <c:if test="${fn:length(errors) gt 0}">
                <p>Please correct the following errors in your submission:</p>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <form action="${pageContext.request.contextPath}/client/create" method="POST">
                <div class="form-group">
                    <label for="companyName">Company Name:</label>
                    <input id="companyName" class="form-control" type="text" name="companyName" value="${client.companyName}"/>
                </div>
                <div class="form-group">
                    <label for="website">Website:</label>
                    <input id="website" class="form-control" type="text" name="website" value="${client.website}"/>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input id="phoneNumber" class="form-control" type="text" name="phoneNumber" value="${client.phoneNumber}"/>
                </div>
                <div class="form-group">
                    <label for="people">Contacts:</label>
                    <select id="people" class="form-control selectpicker" data-live-search="true" multiple name="personId">
                        <c:forEach items="${people}" var="person">
                            <c:if test="${person.client.clientId == null}">
                                <option value="${person.personId}" ${client.clientId != null && person.client.clientId == client.clientId ? 'selected="selected"' : ''}>${person.firstName} ${person.lastName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <h2>Physical Address</h2>
                <input type="hidden" name="addressType" value="${physicalAddress.addressType}"/>
                <div class="form-group">
                    <label for="physicalStreetAddress">Street Address:</label>
                    <input id="physicalStreetAddress" class="form-control" type="text" name="streetAddress" value="${physicalAddress.streetAddress}"/>
                </div>
                <div class="form-group">
                    <label for="physicalCity">City:</label>
                    <input id="physicalCity" class="form-control" type="text" name="city" value="${physicalAddress.city}"/>
                </div>
                <div class="form-group">
                    <label for="physicalState">State:</label>
                    <input id="physicalState" class="form-control" type="text" name="state" value="${physicalAddress.state}"/>
                </div>
                <div class="form-group">
                    <label for="physicalZipCode">Zip Code:</label>
                    <input id="physicalZipCode" class="form-control" type="text" name="zipCode" value="${physicalAddress.zipCode}"/>
                </div>
                <h2>Mailing Address</h2>
                <input type="hidden" name="addressType" value="${mailingAddress.addressType}"/>
                <div class="form-group">
                    <label for="mailingStreetAddress">Street Address:</label>
                    <input id="mailingStreetAddress" class="form-control" type="text" name="streetAddress" value="${mailingAddress.streetAddress}"/>
                </div>
                <div class="form-group">
                    <label for="mailingCity">City:</label>
                    <input id="mailingCity" class="form-control" type="text" name="city" value="${mailingAddress.city}"/>
                </div>
                <div class="form-group">
                    <label for="mailingState">State:</label>
                    <input id="mailingState" class="form-control" type="text" name="state" value="${mailingAddress.state}"/>
                </div>
                <div class="form-group">
                    <label for="mailingZipCode">Zip Code:</label>
                    <input id="mailingZipCode" class="form-control" type="text" name="zipCode" value="${mailingAddress.zipCode}"/>
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
