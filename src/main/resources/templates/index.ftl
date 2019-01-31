<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <div id="app">

        <form action="/logout" method="post">
            <input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
        </form>

        <form action="/" method="post" >
            <input name="text" placeholder="Write Message Here"/>
            <input name="tag" placeholder="Write Tag Here"/>
            <input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">Send</button>
        </form>

        <div>
            <p>Filter by tag</p>
            <form action="/filter" method="post">
                <input type="text" name="msg_tag_filter" placeholder="Filter by tag">
                <input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Filter</button>
            </form>
        </div>

        <div>
            <#list messages>
                <ul>
                    <#items as m>
                        <li>
                            <b>${m.id}</b>
                            ${m.text}
                            <i>#${m.tag}</i>
                        </li>
                    </#items>
                </ul>
            </#list>
        </div>
    </div>
</body>
</html>