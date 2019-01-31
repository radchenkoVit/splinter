<#macro logout>
    <form action="/logout" method="post">
<#--<input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
    <input type="submit" value="Sign Out"/>
    </form>
</#macro>