<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Client</title>
    </head>
    <body>
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
            <br/>
            <label for="companyName">Company Name:</label>
            <input id="companyName" type="text" name="companyName" value="${client.companyName}"/>
            <br/>
            <label for="website">Website:</label>
            <input id="website" type="text" name="website" value="${client.website}"/>
            <br/>
            <label for="phoneNumber">Phone Number:</label>
            <input id="phoneNumber" type="text" name="phoneNumber" value="${client.phoneNumber}"/>
            <br/>
            <h2>Physical Address</h2>
            <input type="hidden" name="addressType" value="${physicalAddress.addressType}"/>
            <label for="physicalStreetAddress">Street Address:</label>
            <input id="physicalStreetAddress" type="text" name="streetAddress" value="${physicalAddress.streetAddress}"/>
            <br/>
            <label for="physicalCity">City:</label>
            <input id="physicalCity" type="text" name="city" value="${physicalAddress.city}"/>
            <br/>
            <label for="physicalState">State:</label>
            <input id="physicalState" type="text" name="state" value="${physicalAddress.state}"/>
            <br/>
            <label for="physicalZipCode">Zip Code:</label>
            <input id="physicalZipCode" type="text" name="zipCode" value="${physicalAddress.zipCode}"/>
            <br/>
            <h2>Mailing Address</h2>
            <input type="hidden" name="addressType" value="${mailingAddress.addressType}"/>
            <label for="mailingStreetAddress">Street Address:</label>
            <input id="mailingStreetAddress" type="text" name="streetAddress" value="${mailingAddress.streetAddress}"/>
            <br/>
            <label for="mailingCity">City:</label>
            <input id="mailingCity" type="text" name="city" value="${mailingAddress.city}"/>
            <br/>
            <label for="mailingState">State:</label>
            <input id="mailingState" type="text" name="state" value="${mailingAddress.state}"/>
            <br/>
            <label for="mailingZipCode">Zip Code:</label>
            <input id="mailingZipCode" type="text" name="zipCode" value="${mailingAddress.zipCode}"/>
            <br/>
            <input type="submit" name="Submit" value="Submit"/>
        </form>
    </body>
</html>
