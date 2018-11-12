<%-- 
    Document   : create
    Created on : Apr 22, 2011, 3:24:13 PM
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

        <title>Create Person</title>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="container">
            <h1>Create Person</h1>
            <c:if test="${fn:length(errors) gt 0}">
                <p>Please correct the following errors in your submission:</p>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <form action="${pageContext.request.contextPath}/person/create" method="POST">
                <input type="hidden" name="addressType" value="${address.addressType}"/>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="firstName">First Name:</label>
                        <input id="firstName" class="form-control" type="text" name="firstName" value="${person.firstName}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="lastName">Last Name:</label>
                        <input id="lastName" class="form-control" type="text" name="lastName" value="${person.lastName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="emailAddress">Email Address:</label>
                    <input id="emailAddress" class="form-control" type="text" name="emailAddress" value="${person.emailAddress}"/>
                </div>
                <div class="form-group">
                    <label for="streetAddress">Street Address:</label>
                    <input id="streetAddress" class="form-control" type="text" name="streetAddress" value="${person.address.streetAddress}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="city">City:</label>
                        <input id="city" class="form-control" type="text" name="city" value="${address.city}"/>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="state">State:</label>
                        <select id="state" class="form-control" name="state">
                            <option ${"AL".equals(address.state)? 'selected="selected"' : ''} value="AL">AL</option>
                            <option ${"AK".equals(address.state)? 'selected="selected"' : ''} value="AK">AK</option>
                            <option ${"AZ".equals(address.state)? 'selected="selected"' : ''} value="AZ">AZ</option>
                            <option ${"AR".equals(address.state)? 'selected="selected"' : ''} value="AR">AR</option>
                            <option ${"CA".equals(address.state)? 'selected="selected"' : ''} value="CA">CA</option>
                            <option ${"CO".equals(address.state)? 'selected="selected"' : ''} value="CO">CO</option>
                            <option ${"CT".equals(address.state)? 'selected="selected"' : ''} value="CT">CT</option>
                            <option ${"DE".equals(address.state)? 'selected="selected"' : ''} value="DE">DE</option>
                            <option ${"DC".equals(address.state)? 'selected="selected"' : ''} value="DC">DC</option>
                            <option ${"FL".equals(address.state)? 'selected="selected"' : ''} value="FL">FL</option>
                            <option ${"GA".equals(address.state)? 'selected="selected"' : ''} value="GA">GA</option>
                            <option ${"HI".equals(address.state)? 'selected="selected"' : ''} value="HI">HI</option>
                            <option ${"ID".equals(address.state)? 'selected="selected"' : ''} value="ID">ID</option>
                            <option ${"IL".equals(address.state)? 'selected="selected"' : ''} value="IL">IL</option>
                            <option ${"IN".equals(address.state)? 'selected="selected"' : ''} value="IN">IN</option>
                            <option ${"IA".equals(address.state)? 'selected="selected"' : ''} value="IA">IA</option>
                            <option ${"KS".equals(address.state)? 'selected="selected"' : ''} value="KS">KS</option>
                            <option ${"KY".equals(address.state)? 'selected="selected"' : ''} value="KY">KY</option>
                            <option ${"LA".equals(address.state)? 'selected="selected"' : ''} value="LA">LA</option>
                            <option ${"ME".equals(address.state)? 'selected="selected"' : ''} value="ME">ME</option>
                            <option ${"MD".equals(address.state)? 'selected="selected"' : ''} value="MD">MD</option>
                            <option ${"MA".equals(address.state)? 'selected="selected"' : ''} value="MA">MA</option>
                            <option ${"MI".equals(address.state)? 'selected="selected"' : ''} value="MI">MI</option>
                            <option ${"MN".equals(address.state)? 'selected="selected"' : ''} value="MN">MN</option>
                            <option ${"MS".equals(address.state)? 'selected="selected"' : ''} value="MS">MS</option>
                            <option ${"MO".equals(address.state)? 'selected="selected"' : ''} value="MO">MO</option>
                            <option ${"MT".equals(address.state)? 'selected="selected"' : ''} value="MT">MT</option>
                            <option ${"NE".equals(address.state)? 'selected="selected"' : ''} value="NE">NE</option>
                            <option ${"NV".equals(address.state)? 'selected="selected"' : ''} value="NV">NV</option>
                            <option ${"NH".equals(address.state)? 'selected="selected"' : ''} value="NH">NH</option>
                            <option ${"NJ".equals(address.state)? 'selected="selected"' : ''} value="NJ">NJ</option>
                            <option ${"NM".equals(address.state)? 'selected="selected"' : ''} value="NM">NM</option>
                            <option ${"NY".equals(address.state)? 'selected="selected"' : ''} value="NY">NY</option>
                            <option ${"NC".equals(address.state)? 'selected="selected"' : ''} value="NC">NC</option>
                            <option ${"ND".equals(address.state)? 'selected="selected"' : ''} value="ND">ND</option>
                            <option ${"OH".equals(address.state)? 'selected="selected"' : ''} value="OH">OH</option>
                            <option ${"OK".equals(address.state)? 'selected="selected"' : ''} value="OK">OK</option>
                            <option ${"OR".equals(address.state)? 'selected="selected"' : ''} value="OR">OR</option>
                            <option ${"PA".equals(address.state)? 'selected="selected"' : ''} value="PA">PA</option>
                            <option ${"RI".equals(address.state)? 'selected="selected"' : ''} value="RI">RI</option>
                            <option ${"SC".equals(address.state)? 'selected="selected"' : ''} value="SC">SC</option>
                            <option ${"SD".equals(address.state)? 'selected="selected"' : ''} value="SD">SD</option>
                            <option ${"TN".equals(address.state)? 'selected="selected"' : ''} value="TN">TN</option>
                            <option ${"TX".equals(address.state)? 'selected="selected"' : ''} value="TX">TX</option>
                            <option ${"UT".equals(address.state)? 'selected="selected"' : ''} value="UT">UT</option>
                            <option ${"VT".equals(address.state)? 'selected="selected"' : ''} value="VT">VT</option>
                            <option ${"VA".equals(address.state)? 'selected="selected"' : ''} value="VA">VA</option>
                            <option ${"WA".equals(address.state)? 'selected="selected"' : ''} value="WA">WA</option>
                            <option ${"WV".equals(address.state) ? 'selected="selected"' : ''} value="WV">WV</option>
                            <option ${"WI".equals(address.state) ? 'selected="selected"' : ''} value="WI">WI</option>
                            <option ${"WY".equals(address.state) ? 'selected="selected"' : ''} value="WY">WY</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="zipCode">Zip Code:</label>
                        <input id="zipCode" class="form-control" type="text" name="zipCode" value="${address.zipCode}"/>
                    </div>
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
