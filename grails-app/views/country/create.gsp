<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main">
    <title>Add a new Country</title>
</head>

<body>
<h1>Country-Creation</h1>
<g:hasErrors bean="${countryInstance}">
    <ul class="errors" role="alert">
        <g:eachError bean="${countryInstance}" var="error">
            <div class="alert alert-danger">
                <li><g:message error="${error}"/></li>
            </div>
        </g:eachError>
    </ul>
</g:hasErrors>

    <g:form name="createCountryForm" action="save">
        <label for="name">Name of Country</label>
        <g:textField name="name" class="form-control" />

        <g:submitButton name="submit" value="Create Country" class="form-control btn btn-primary" />
    </g:form>

    <a class="form-control btn btn-warning" href="#" onclick="javascript:window.history.back();">Back</a>

</body>
</html>