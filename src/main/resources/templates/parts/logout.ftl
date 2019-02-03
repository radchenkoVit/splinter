<#macro logout>
    <form action="/logout" method="post">
<#--<input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>