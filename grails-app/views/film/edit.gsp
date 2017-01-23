<%@ page import="asta.nielsen.Film; asta.nielsen.Language" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Edit Film</title>
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

<h2>Edit existing Film</h2>
<g:form name="editFilmForm" url="[resource:filmInstance, action:'update']" method="PUT">
    <div class="form-group ${hasErrors(bean: filmInstance, field: 'originalTitle', 'has-danger')}" >
        <label for="originalTitle">Original Film Title</label>
        <g:textField name="originalTitle" id="originalTitle" value="${filmInstance.originalTitle}"
                     class="form-control ${hasErrors(bean: filmInstance, field: 'originalTitle', 'form-control-danger')}" />
    </div>

    <div class="form-group">
        <label for="originalLanguage">Language</label>
        <g:select name="originalLanguage"
                  from="${asta.nielsen.Language.list()}"
                  optionKey="id"
                  class="form-control"
                  value="${filmInstance?.originalLanguage?.id}"/>
    </div>

    <g:link controller="language" action="create" class="form-control btn btn-link">Create new Language</g:link>

    <div class="form-group">
        <label for="originalCountry">Country</label>
        <g:select name="originalCountry"
                  from="${asta.nielsen.Country.list()}"
                  optionKey="id"
                  class="form-control"
                  value="${filmInstance?.originalCountry?.id}"/>
    </div>

    <g:link controller="country" action="create" class="form-control btn btn-link">Create new Country</g:link>

    <div class="form-group">
        <label for="date">Date</label>

        <g:datePicker name="date"
                      default="${Date.parse("yyyy-MM-dd hh:mm:ss", "1910-01-01 1:23:45")}"
                      precision="day"
                      value="${filmInstance.date}" />
    </div>

    <g:each in="${filmInstance.distributionTitles}">
        <p>Title: ${it.title}</p>
        <p>Language: ${it.language}</p>
    </g:each>

    <div class="form-group">
        <input type="submit" class="form-control btn btn-primary" value="Save Changes" />
    </div>
    <div class="form-group">
        <g:link action="index" class="form-control btn btn-warning">Back to Overview</g:link>
    </div>
</g:form>
</body>
</html>