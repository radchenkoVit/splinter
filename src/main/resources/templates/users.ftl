<#import "parts/common.ftl" as c>
<#import "parts/logout.ftl" as l>

<@c.page>
    <div id="app">
        <@l.logout/>

    <p>List of users</p>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Role</th>
                <th></th>
            </tr>
        </thead>
    <tbody>
        <#list users as user>
            <tr>
                <td>${user.firstName}</td>
                <td><#list user.roles as role> ${role.name}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">Edit</a></td>
            </tr>
        </#list>
    </tbody>
    </table>
    </div>
</@c.page>