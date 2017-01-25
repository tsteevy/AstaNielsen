<%@ page import="asta.nielsen.Country; asta.nielsen.Film; asta.nielsen.Language" contentType="text/html;charset=UTF-8" %>


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

<div class="input-group">
    <g:submitToRemote url="[resource: film, action: 'addFilmTitle']" update="updatableTitleList"
                      class="btn btn-primary" value="Add"
                      onSuccess="\$('#addAlternativeTitleForm')[0].reset();\$('#title')[0].focus()"/>
</div>

