<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Film Overview</title>
</head>

<body>
    <h2>Create new Film</h2>
    <g:form name="createFilmForm" action="save">
        <div class="form-group">
            <label for="originalFilmTitle">Original Film Title</label>
            <g:textField name="originalFilmTitle" id="originalFilmTitle" class="form-control" />
        </div>

        <div class="form-group">
            <label for="otherTitles">Other Distribution Titles:</label>
            <g:select class="form-control"
                      optionKey="id"
                      optionValue="name"
                      name="otherTitles"
                      from="${film?.distributionTitles}"
                      noSelection="['':'-Choose Titles-']" />
        </div>

        <div class="form-group">
            <input type="submit" class="form-control btn btn-primary" value="Create" />
        </div>
    </g:form>
</body>
</html>