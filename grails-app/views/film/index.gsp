<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Film Overview</title>
</head>

<body>
    <h2>List of existing films</h2>

<ul class="list-group">

    <g:if test="${films.size() > 0}">
        <g:each in="${films}">
            <g:render template="filmrow" model="[film: it]" />
        </g:each>
    </g:if>
    <g:else>
        <button type="button" class="list-group-item list-group-item-action">no films yet.</button>
    </g:else>
    <g:link action="create" class="list-group-item list-group-item-action active">Create a new film</g:link>
</ul>

</body>
</html>