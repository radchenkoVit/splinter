<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div id="app">
    <form action="/login" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</div>
</body>
</html>