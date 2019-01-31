<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users Page</title>
</head>
    <body>
        <div id="app">
            <#list users>
            <ul>
                <#items as u>
                <li>${u.name}</li>
                </#items>
            </ul>
            </#list>
        </div>
    </body>
</html>