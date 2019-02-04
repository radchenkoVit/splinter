<#import "parts/common.ftl" as c>
<#import "parts/logout.ftl" as l>

<@c.page>
    <div id="app">
        User Editor
    <form action="/user/edit" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="text" name="firstName" value="${user.firstName}"/>
    <input type="text" name="lastName" value="${user.lastName}"/>
    <input type="text" name="email" value="${user.email}"/>
    <#list roles as role>
        <label>
            <input type="checkbox" name="${role} ${user.roles?seq_contains(role)?string("checked", "")}">${role}</input>
        </label>
    </#list>
    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
    <input type="submit" value="Save"/>
    </form>
    </div>
</@c.page>