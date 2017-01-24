<button type="button" class="list-group-item list-group-item-action"  data-toggle="collapse" data-target="#buttons_for_${film.id}">${film.id} -  ${film.originalTitle}</button>
<div id="buttons_for_${film.id}" class="collapse">
    <g:link action="edit" id="${film.id}" resource="film" class="list-group-item list-group-item-warning">edit this film</g:link>
    <g:link action="delete" id="${film.id}" class="list-group-item list-group-item-danger">remove this film</g:link>
</div>