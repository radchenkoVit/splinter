<#import "parts/common.ftl" as c>

<@c.page>
    <div id="app">
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