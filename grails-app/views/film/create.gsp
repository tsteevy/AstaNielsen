<%@ page import="asta.nielsen.Language" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Film Creation</title>
</head>

<body>
    <h2>Create new Film</h2>
    <g:form name="createFilmForm" action="save">
        <div class="form-group">
            <label for="originalFilmTitle">Original Film Title</label>
            <g:textField name="originalFilmTitle" id="originalFilmTitle" class="form-control" />
        </div>

        <div class="form-group">
            <label for="language">Language</label>
            <g:select name="language"
                      from="${asta.nielsen.Language.list()}"
                      optionKey="id"
                      class="form-control"/>
        </div>

        <g:link controller="language" action="create" class="form-control btn btn-primary">Create new Language</g:link>

        <div class="form-group">
            <label for="country">Country</label>
            <g:select name="country"
                      from="${asta.nielsen.Country.list()}"
                      optionKey="id"
                      class="form-control"/>
        </div>

        <g:link controller="country" action="create" class="form-control btn btn-primary">Create new Country</g:link>

        <div class="form-group">
            <g:datePicker name="date"
                          default="${Date.parse("yyyy-MM-dd hh:mm:ss", "1910-01-01 1:23:45")}"
                          precision="day"
                          class="form-control"/>
        </div>



        <div class="form-group">
            <input type="submit" class="form-control btn btn-primary" value="Create" />
        </div>
        <div class="form-group">
            <g:link action="index" class="form-control btn btn-warning">back</g:link>
        </div>
    </g:form>
</body>
</html>