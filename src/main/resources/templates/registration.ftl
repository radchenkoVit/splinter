<#import "parts/common.ftl" as c>

<@c.page>
    <div id="app">
    <div>
        <#if message??>
            ${message}
        </#if>
    </div>

        <form action="/registration" method="post">
            <div class="form-group">
                <label for="fn_input">First name</label>
                <input type="text" class="form-control" id="fn_input" name="firstName" aria-describedby="emailHelp" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label for="ln_input">Last name</label>
                <input type="password" class="form-control" id="ln_input" name="lastName" placeholder="Enter last name">
            </div>
            <div class="form-group">
                <label for="em_input">Email address</label>
                <input type="email" class="form-control" id="em_input" name="email" aria-describedby="emailHelp" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="pass_input">Password</label>
                <input type="password" class="form-control" id="pass_input" name="password" placeholder="Password">
            </div>
            <#--<input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</@c.page>