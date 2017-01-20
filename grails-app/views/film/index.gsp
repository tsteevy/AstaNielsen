<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Film Overview</title>
</head>

<body>
    <h2>List of existing films</h2>
    <g:each in="${films}">
        <p>Title: ${it.originalTitle}</p>
    </g:each>

    <g:link action="create" class="btn btn-primary">Create a new film</g:link>
</body>
</html>