<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div id="app">
    <form action="/registration" method="post">
        <div><label> Name : <input type="text" name="firstName"/> </label></div>
        <div><label> Email : <input type="text" name="email"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</div>
</body>
</html>