<#import "parts/common.ftl" as c>

<@c.page>
    <div id="app">
        <div>
            ${message?ifExists}
        </div>

        <form action="/login" method="post">
            <div class="form-group">
                <label for="username">Email address</label>
                <input type="email" name="username" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control" id="password" placeholder="Password">
            </div>
            <div>
                <input class="btn btn-primary" type="submit" value="Sign In"/>
                <a href="/registration" role="button" class="btn btn-primary">Register</a>
            </div>
        </form>
    </div>
</@c.page>