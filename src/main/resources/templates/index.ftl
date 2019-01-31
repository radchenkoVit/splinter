<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <div id="app">
        <form action="/" method="post" >
            <input name="text" placeholder="Write Message Here"/>
            <input name="tag" placeholder="Write Tag Here"/>
            <button type="submit">Send</button>
        </form>

        <div>
            <p>Filter by tag</p>
            <form action="/filter">
                <input type="text" name="msg_tag_filter" placeholder="Filter by tag">
                <button type="submit">Filter</button>
            </form>
        </div>

        <div>
            <#list messages>
                <ul>
                    <#items as m>
                        <li>${m.text}#${m.tag}</li>
                    </#items>
                </ul>
            </#list>
        </div>
    </div>
</body>
</html>