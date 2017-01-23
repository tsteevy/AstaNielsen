<%@ page import="asta.nielsen.Country; asta.nielsen.Film; asta.nielsen.Language" contentType="text/html;charset=UTF-8" %>
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
<g:form name="editFilmForm" id="editFilmForm" url="[resource: filmInstance, action: 'update']" method="PUT">
    <div class="form-group ${hasErrors(bean: filmInstance, field: 'originalTitle', 'has-danger')}">
        <label for="originalTitle">Original Film Title</label>
        <g:textField name="originalTitle" id="originalTitle" value="${filmInstance.originalTitle}"
                     class="form-control ${hasErrors(bean: filmInstance, field: 'originalTitle', 'form-control-danger')}"/>
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
                      value="${filmInstance.date}"/>
    </div>
</g:form>

<h2>Alternative film titles</h2>

<div class="form-group">
    <g:if test="${filmInstance.distributionTitles.size() > 0}">
        <g:each in="${filmInstance.distributionTitles}">
            <button type="button" class="list-group-item list-group-item-action" data-toggle="collapse"
                    data-target="#buttons_for_${it.id}">${it.title} -  ${it.language}</button>

            <div id="buttons_for_${it.id}" class="collapse">
                <g:link action="delete_title" id="${filmInstance.id}" resource="film"
                        class="list-group-item list-group-item-danger"
                        params="[title_id: it.id]">remove this title</g:link>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <button type="button" class="list-group-item list-group-item-action">no alternative titles yet.</button>
    </g:else>
    <g:link action="addFilmTitle" class="list-group-item list-group-item-action active" data-toggle="collapse"
            data-target="#titleForm">Add an alternative title.</g:link>
    <div class="form-group">
        <div id="titleForm" class="collapse">
            <g:form class="form-inline" name="addAlternativeTitleForm" id="addAlternativeTitleForm"
                    url="[resource: filmInstance, action: 'addFilmTitle']" method="PUT">
                <label class="sr-only" for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Other title">

                <label class="sr-only" for="language">Language</label>

                <div class="input-group">
                    <g:select id="language" name='language' value="${language?.id}"
                              noSelection="${['null': 'Select Language']}"
                              from='${Language.list()}'
                              optionKey="id" optionValue="name" class="form-control"></g:select>
                </div>

                <label class="sr-only" for="country">Country</label>

                <div class="input-group">
                    <g:select id="country" name='country' value="${country?.id}"
                              noSelection="${['null': 'Select Country']}"
                              from='${asta.nielsen.Country.list()}'
                              optionKey="id" optionValue="name" class="form-control"></g:select>
                </div>

                <button type="submit" class="btn btn-primary">Add</button>
            </g:form>
        </div>
    </div>
</div>

<div class="form-group">
    <input type="button" class="form-control btn btn-primary" onclick="$('#editFilmForm').submit()"
           value="Save Changes"/>
</div>

<div class="form-group">
    <g:link action="index" class="form-control btn btn-warning">Back to Overview</g:link>
</div>

</body>
</html>