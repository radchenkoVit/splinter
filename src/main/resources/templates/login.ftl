<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div id="app">

    <div>
        <#if message??>
            <p>message</p>
        </#if>
    </div>

    <form action="/login" method="post">
        <div><label> Email : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div><input type="submit" value="Sign In"/></div>
    </form>

    <form action="/registration" method="get">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>