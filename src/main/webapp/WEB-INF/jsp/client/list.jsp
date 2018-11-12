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
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <spring:url value="/resources/css/layout.css" var="layoutCSS" />
        <link rel="stylesheet" href="${layoutCSS}">

        <title>Client Listing</title>
    </head>
    <body>
        <%@include file="../layout.jsp" %>
        <div class="container">
            <h1>Client Listing</h1>
            <nav class="nav">
                <a class="nav-link" href="${pageContext.request.contextPath}/client/create">Create New Client</a>
            </nav>
            <c:choose>
                <c:when test="${fn:length(clients) gt 0}">
                    <table id="client-table" class="table">
                        <thead>
                            <tr>
                                <th>Company Name</th>
                                <th>Website</th>
                                <th>Phone Number</th>
                                <th>Contacts</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${clients}" var="client">
                                <tr>
                                    <td class="align-middle"><a href="${pageContext.request.contextPath}/client/edit/${client.clientId}">${client.companyName}</a></td>
                                    <td class="align-middle">${client.website}</td>
                                    <td class="align-middle">${client.phoneNumber}</td>
                                    <td class="align-middle">
                                        <c:forEach items="${people}" var="person">
                                            <c:if test="${person.client.clientId == client.clientId}">
                                                <div><a href="${pageContext.request.contextPath}/person/edit/${person.personId}">${person.firstName} ${person.lastName}</a></div>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <nav class="nav">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/client/edit/${client.clientId}">Edit Client</a>
                                            <a class="nav-link" href="${pageContext.request.contextPath}/client/delete/${client.clientId}">Delete Client</a>
                                        </nav>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No results found.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#client-table').DataTable();
                $('#nav-client').addClass('active');
            });
        </script>
    </body>
</html>
