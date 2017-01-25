<g:if test="${filmInstance?.distributionTitles?.size() > 0}">
    <g:each in="${filmInstance.distributionTitles}">
        <button type="button" class="list-group-item list-group-item-action" data-toggle="collapse"
                data-target="#buttons_for_${it.id}">${it.title} -  ${it.language}</button>

        <div id="buttons_for_${it.id}" class="collapse">
            <g:link action="deleteTitle" id="${filmInstance.id}" resource="film"
                    class="list-group-item list-group-item-danger"
                    params="[title_id: it.id]">remove this title</g:link>
        </div>
    </g:each>
</g:if>
<g:else>
    <button type="button" class="list-group-item list-group-item-action">no alternative titles yet.</button>
</g:else>