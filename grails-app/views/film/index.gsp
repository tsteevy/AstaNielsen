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
            <button type="button" class="list-group-item list-group-item-action"  data-toggle="collapse" data-target="#buttons_for_${it.id}">${it.id} -  ${it.originalTitle}</button>
            <div id="buttons_for_${it.id}" class="collapse">
                <g:link action="edit" id="${it.id}" class="list-group-item list-group-item-warning">edit this film</g:link>
                <g:link action="delete" id="${it.id}" class="list-group-item list-group-item-danger">remove this film</g:link>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <button type="button" class="list-group-item list-group-item-action">no films yet.</button>
    </g:else>
    <g:link action="create" class="list-group-item list-group-item-action active">Create a new film</g:link>
</ul>

</body>
</html>