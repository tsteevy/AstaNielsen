<%@ page import="asta.nielsen.Language" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Film Creation</title>
</head>

<body>
<g:hasErrors bean="${filmInstance}">
    <ul class="errors" role="alert">
        <g:eachError bean="${filmInstance}" var="error">
            <div class="alert alert-danger">
                <li><g:message error="${error}"/></li>
            </div>
        </g:eachError>
    </ul>
</g:hasErrors>

    <h2>Create new Film</h2>
    <g:form name="createFilmForm" action="save">
        <div class="form-group ${hasErrors(bean: filmInstance, field: 'originalTitle', 'has-danger')}" >
            <label for="originalTitle">Original Film Title</label>
            <g:textField name="originalTitle" id="originalTitle"
                         class="form-control ${hasErrors(bean: filmInstance, field: 'originalTitle', 'form-control-danger')}" />
        </div>

        <div class="form-group">
            <label for="originalLanguage">Language</label>
            <g:select name="originalLanguage"
                      from="${asta.nielsen.Language.list()}"
                      optionKey="id"
                      class="form-control"
                      value="${filmInstance.originalLanguage}"/>
        </div>

        <g:link controller="language" action="create" class="form-control btn btn-link">Create new Language</g:link>

        <div class="form-group">
            <label for="originalCountry">Country</label>
            <g:select name="originalCountry"
                      from="${asta.nielsen.Country.list()}"
                      optionKey="id"
                      class="form-control"/>
        </div>

        <g:link controller="country" action="create" class="form-control btn btn-link">Create new Country</g:link>

        <div class="form-group">
            <label for="date">Date</label>

            <g:datePicker name="date"
                          default="${Date.parse("yyyy-MM-dd hh:mm:ss", "1910-01-01 1:23:45")}"
                          precision="day"
                          value="${filmInstance.date}" />
        </div>



        <div class="form-group">
            <input type="submit" class="form-control btn btn-primary" value="Create Film" />
        </div>
        <div class="form-group">
            <g:link action="index" class="form-control btn btn-warning">Back to Overview</g:link>
        </div>
    </g:form>
</body>
</html>