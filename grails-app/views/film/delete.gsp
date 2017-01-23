<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Are you sure you want to remove this film? (${filmInstance.id})</title>
</head>

<body>
<div class="container">
    <h1>are you sure you want to remove the film: '${filmInstance.originalTitle}'</h1>

    <g:form url="[resource:filmInstance, action:'remove']" method="DELETE" name="deleteFilmConfirmation" action="remove">
        <g:actionSubmit class="form-control btn btn-danger" action="remove" value="Delete" />
    </g:form>

    <g:link action="index" class="btn btn-block btn-default">Abort and return to list</g:link>
</div>

</body>
</html>