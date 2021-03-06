<spring:url value="/resources/aquent-logo.png" var="logoPNG" />
<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="navbar-brand"><img class="img-fluid" src="${logoPNG}" alt="Aquent logo"></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li id="nav-people" class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/person/list">People</a>
            </li>
            <li id="nav-client" class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/client/list">Clients</a>
            </li>
        </ul>
    </div>
</nav>