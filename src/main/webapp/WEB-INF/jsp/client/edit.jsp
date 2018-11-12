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

        <spring:url value="/resources/css/layout.css" var="layoutCSS" />
        <spring:url value="/resources/js/formValidation.js" var="formValidationJS" />
        <link href="${layoutCSS}" rel="stylesheet">

        <title>Edit Client</title>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="container">
            <h1>Edit Client</h1>
            <c:if test="${fn:length(errors) gt 0}">
                <p>Please correct the following errors in your submission:</p>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <form action="${pageContext.request.contextPath}/client/edit" method="POST" class="needs-validation" novalidate>
                <input type="hidden" name="clientId" value="${client.clientId}"/>
                <div class="form-group">
                    <label for="companyName">Company Name:</label>
                    <input id="companyName" class="form-control" type="text" name="companyName" value="${client.companyName}" placeholder="Company Name" required maxlength="100"/>
                    <div class="invalid-feedback">Company name is required with maximum length of 100</div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-9">
                        <label for="website">Website:</label>
                        <input id="website" class="form-control" type="text" name="website" value="${client.website}" placeholder="Website URI" required maxlength="100"/>
                        <div class="invalid-feedback">Website is required with maximum length of 100</div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="phoneNumber">Phone Number:</label>
                        <input id="phoneNumber" class="form-control" type="text" name="phoneNumber" value="${client.phoneNumber}" required maxlength="10" pattern="\d{7,10}"/>
                        <div class="invalid-feedback">Phone number is required with 7 to 10 digits</div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="people">Contacts:</label>
                    <select id="people" class="form-control selectpicker" data-live-search="true" multiple name="personId">
                        <c:forEach items="${people}" var="person">
                            <c:if test="${person.client.clientId == null || person.client.clientId == client.clientId}">
                                <option value="${person.personId}" ${person.client.clientId == client.clientId ? 'selected="selected"' : ''}>${person.firstName} ${person.lastName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <h2>Physical Address</h2>
                <input type="hidden" name="addressId" value="${physicalAddress.addressId}"/>
                <input type="hidden" name="addressType" value="${physicalAddress.addressType}"/>
                <div class="form-group">
                    <label for="physicalStreetAddress">Street Address:</label>
                    <input id="physicalStreetAddress" class="form-control" type="text" name="streetAddress" value="${physicalAddress.streetAddress}" placeholder="Street Address" required maxlength="50"/>
                    <div class="invalid-feedback">Street address is required with maximum length of 50</div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="physicalCity">City:</label>
                        <input id="physicalCity" class="form-control" type="text" name="city" value="${physicalAddress.city}" placeholder="City" required maxlength="50"/>
                        <div class="invalid-feedback">City is required with maximum length of 50</div>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="physicalState">State:</label>
                        <select id="physicalState" class="form-control" name="state" required>
                            <option ${"AL".equals(physicalAddress.state)? 'selected="selected"' : ''} value="AL">AL</option>
                            <option ${"AK".equals(physicalAddress.state)? 'selected="selected"' : ''} value="AK">AK</option>
                            <option ${"AZ".equals(physicalAddress.state)? 'selected="selected"' : ''} value="AZ">AZ</option>
                            <option ${"AR".equals(physicalAddress.state)? 'selected="selected"' : ''} value="AR">AR</option>
                            <option ${"CA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="CA">CA</option>
                            <option ${"CO".equals(physicalAddress.state)? 'selected="selected"' : ''} value="CO">CO</option>
                            <option ${"CT".equals(physicalAddress.state)? 'selected="selected"' : ''} value="CT">CT</option>
                            <option ${"DE".equals(physicalAddress.state)? 'selected="selected"' : ''} value="DE">DE</option>
                            <option ${"DC".equals(physicalAddress.state)? 'selected="selected"' : ''} value="DC">DC</option>
                            <option ${"FL".equals(physicalAddress.state)? 'selected="selected"' : ''} value="FL">FL</option>
                            <option ${"GA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="GA">GA</option>
                            <option ${"HI".equals(physicalAddress.state)? 'selected="selected"' : ''} value="HI">HI</option>
                            <option ${"ID".equals(physicalAddress.state)? 'selected="selected"' : ''} value="ID">ID</option>
                            <option ${"IL".equals(physicalAddress.state)? 'selected="selected"' : ''} value="IL">IL</option>
                            <option ${"IN".equals(physicalAddress.state)? 'selected="selected"' : ''} value="IN">IN</option>
                            <option ${"IA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="IA">IA</option>
                            <option ${"KS".equals(physicalAddress.state)? 'selected="selected"' : ''} value="KS">KS</option>
                            <option ${"KY".equals(physicalAddress.state)? 'selected="selected"' : ''} value="KY">KY</option>
                            <option ${"LA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="LA">LA</option>
                            <option ${"ME".equals(physicalAddress.state)? 'selected="selected"' : ''} value="ME">ME</option>
                            <option ${"MD".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MD">MD</option>
                            <option ${"MA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MA">MA</option>
                            <option ${"MI".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MI">MI</option>
                            <option ${"MN".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MN">MN</option>
                            <option ${"MS".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MS">MS</option>
                            <option ${"MO".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MO">MO</option>
                            <option ${"MT".equals(physicalAddress.state)? 'selected="selected"' : ''} value="MT">MT</option>
                            <option ${"NE".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NE">NE</option>
                            <option ${"NV".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NV">NV</option>
                            <option ${"NH".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NH">NH</option>
                            <option ${"NJ".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NJ">NJ</option>
                            <option ${"NM".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NM">NM</option>
                            <option ${"NY".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NY">NY</option>
                            <option ${"NC".equals(physicalAddress.state)? 'selected="selected"' : ''} value="NC">NC</option>
                            <option ${"ND".equals(physicalAddress.state)? 'selected="selected"' : ''} value="ND">ND</option>
                            <option ${"OH".equals(physicalAddress.state)? 'selected="selected"' : ''} value="OH">OH</option>
                            <option ${"OK".equals(physicalAddress.state)? 'selected="selected"' : ''} value="OK">OK</option>
                            <option ${"OR".equals(physicalAddress.state)? 'selected="selected"' : ''} value="OR">OR</option>
                            <option ${"PA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="PA">PA</option>
                            <option ${"RI".equals(physicalAddress.state)? 'selected="selected"' : ''} value="RI">RI</option>
                            <option ${"SC".equals(physicalAddress.state)? 'selected="selected"' : ''} value="SC">SC</option>
                            <option ${"SD".equals(physicalAddress.state)? 'selected="selected"' : ''} value="SD">SD</option>
                            <option ${"TN".equals(physicalAddress.state)? 'selected="selected"' : ''} value="TN">TN</option>
                            <option ${"TX".equals(physicalAddress.state)? 'selected="selected"' : ''} value="TX">TX</option>
                            <option ${"UT".equals(physicalAddress.state)? 'selected="selected"' : ''} value="UT">UT</option>
                            <option ${"VT".equals(physicalAddress.state)? 'selected="selected"' : ''} value="VT">VT</option>
                            <option ${"VA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="VA">VA</option>
                            <option ${"WA".equals(physicalAddress.state)? 'selected="selected"' : ''} value="WA">WA</option>
                            <option ${"WV".equals(physicalAddress.state) ? 'selected="selected"' : ''} value="WV">WV</option>
                            <option ${"WI".equals(physicalAddress.state) ? 'selected="selected"' : ''} value="WI">WI</option>
                            <option ${"WY".equals(physicalAddress.state) ? 'selected="selected"' : ''} value="WY">WY</option>
                        </select>
                        <div class="invalid-feedback">State is required with length of 2</div>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="physicalZipCode">Zip Code:</label>
                        <input id="physicalZipCode" class="form-control" type="text" name="zipCode" value="${physicalAddress.zipCode}" placeholder="Zip Code" required maxlength="5" pattern="\d{5,5}"/>
                        <div class="invalid-feedback">Zip code is required with 5 digits</div>
                    </div>
                </div>
                <h2>Mailing Address</h2>
                <input type="hidden" name="addressId" value="${mailingAddress.addressId}"/>
                <input type="hidden" name="addressType" value="${mailingAddress.addressType}"/>
                <div class="form-group">
                    <label for="mailingStreetAddress">Street Address:</label>
                    <input id="mailingStreetAddress" class="form-control" type="text" name="streetAddress" value="${mailingAddress.streetAddress}" placeholder="Street Address" required maxlength="50"/>
                    <div class="invalid-feedback">Street address is required with maximum length of 50</div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="mailingCity">City:</label>
                        <input id="mailingCity" class="form-control" type="text" name="city" value="${mailingAddress.city}" placeholder="City" required maxlength="50"/>
                        <div class="invalid-feedback">City is required with maximum length of 50</div>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="mailingState">State:</label>
                        <select id="mailingState" class="form-control" name="state" required>
                            <option ${"AL".equals(mailingAddress.state)? 'selected="selected"' : ''} value="AL">AL</option>
                            <option ${"AK".equals(mailingAddress.state)? 'selected="selected"' : ''} value="AK">AK</option>
                            <option ${"AZ".equals(mailingAddress.state)? 'selected="selected"' : ''} value="AZ">AZ</option>
                            <option ${"AR".equals(mailingAddress.state)? 'selected="selected"' : ''} value="AR">AR</option>
                            <option ${"CA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="CA">CA</option>
                            <option ${"CO".equals(mailingAddress.state)? 'selected="selected"' : ''} value="CO">CO</option>
                            <option ${"CT".equals(mailingAddress.state)? 'selected="selected"' : ''} value="CT">CT</option>
                            <option ${"DE".equals(mailingAddress.state)? 'selected="selected"' : ''} value="DE">DE</option>
                            <option ${"DC".equals(mailingAddress.state)? 'selected="selected"' : ''} value="DC">DC</option>
                            <option ${"FL".equals(mailingAddress.state)? 'selected="selected"' : ''} value="FL">FL</option>
                            <option ${"GA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="GA">GA</option>
                            <option ${"HI".equals(mailingAddress.state)? 'selected="selected"' : ''} value="HI">HI</option>
                            <option ${"ID".equals(mailingAddress.state)? 'selected="selected"' : ''} value="ID">ID</option>
                            <option ${"IL".equals(mailingAddress.state)? 'selected="selected"' : ''} value="IL">IL</option>
                            <option ${"IN".equals(mailingAddress.state)? 'selected="selected"' : ''} value="IN">IN</option>
                            <option ${"IA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="IA">IA</option>
                            <option ${"KS".equals(mailingAddress.state)? 'selected="selected"' : ''} value="KS">KS</option>
                            <option ${"KY".equals(mailingAddress.state)? 'selected="selected"' : ''} value="KY">KY</option>
                            <option ${"LA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="LA">LA</option>
                            <option ${"ME".equals(mailingAddress.state)? 'selected="selected"' : ''} value="ME">ME</option>
                            <option ${"MD".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MD">MD</option>
                            <option ${"MA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MA">MA</option>
                            <option ${"MI".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MI">MI</option>
                            <option ${"MN".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MN">MN</option>
                            <option ${"MS".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MS">MS</option>
                            <option ${"MO".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MO">MO</option>
                            <option ${"MT".equals(mailingAddress.state)? 'selected="selected"' : ''} value="MT">MT</option>
                            <option ${"NE".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NE">NE</option>
                            <option ${"NV".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NV">NV</option>
                            <option ${"NH".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NH">NH</option>
                            <option ${"NJ".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NJ">NJ</option>
                            <option ${"NM".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NM">NM</option>
                            <option ${"NY".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NY">NY</option>
                            <option ${"NC".equals(mailingAddress.state)? 'selected="selected"' : ''} value="NC">NC</option>
                            <option ${"ND".equals(mailingAddress.state)? 'selected="selected"' : ''} value="ND">ND</option>
                            <option ${"OH".equals(mailingAddress.state)? 'selected="selected"' : ''} value="OH">OH</option>
                            <option ${"OK".equals(mailingAddress.state)? 'selected="selected"' : ''} value="OK">OK</option>
                            <option ${"OR".equals(mailingAddress.state)? 'selected="selected"' : ''} value="OR">OR</option>
                            <option ${"PA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="PA">PA</option>
                            <option ${"RI".equals(mailingAddress.state)? 'selected="selected"' : ''} value="RI">RI</option>
                            <option ${"SC".equals(mailingAddress.state)? 'selected="selected"' : ''} value="SC">SC</option>
                            <option ${"SD".equals(mailingAddress.state)? 'selected="selected"' : ''} value="SD">SD</option>
                            <option ${"TN".equals(mailingAddress.state)? 'selected="selected"' : ''} value="TN">TN</option>
                            <option ${"TX".equals(mailingAddress.state)? 'selected="selected"' : ''} value="TX">TX</option>
                            <option ${"UT".equals(mailingAddress.state)? 'selected="selected"' : ''} value="UT">UT</option>
                            <option ${"VT".equals(mailingAddress.state)? 'selected="selected"' : ''} value="VT">VT</option>
                            <option ${"VA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="VA">VA</option>
                            <option ${"WA".equals(mailingAddress.state)? 'selected="selected"' : ''} value="WA">WA</option>
                            <option ${"WV".equals(mailingAddress.state) ? 'selected="selected"' : ''} value="WV">WV</option>
                            <option ${"WI".equals(mailingAddress.state) ? 'selected="selected"' : ''} value="WI">WI</option>
                            <option ${"WY".equals(mailingAddress.state) ? 'selected="selected"' : ''} value="WY">WY</option>
                        </select>
                        <div class="invalid-feedback">State is required with length of 2</div>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="mailingZipCode">Zip Code:</label>
                        <input id="mailingZipCode" class="form-control" type="text" name="zipCode" value="${mailingAddress.zipCode}" placeholder="Zip Code" required maxlength="5" pattern="\d{5,5}"/>
                        <div class="invalid-feedback">Zip code is required with 5 digits</div>
                    </div>
                </div>
                <button class="btn btn-primary" type="submit" name="Submit" value="Submit">Submit</button>
                <br/>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
        <script src="${formValidationJS}" type="text/javascript"></script>
    </body>
</html>
