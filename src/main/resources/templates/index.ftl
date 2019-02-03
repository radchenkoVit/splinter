<#import "parts/common.ftl" as c>
<#import "parts/logout.ftl" as l>

<@c.page>
    <div id="app">
        <@l.logout/>
        <span><a href="/user">Users Page</a></span>

        <form action="/" method="post" enctype="multipart/form-data">
            <input name="text" placeholder="Write Message Here"/>
            <input name="tag" placeholder="Write Tag Here"/>
            <input type="file" name="image_file">
            <#--<input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            <button type="submit">Send</button>
        </form>
        <div>
            <p>Filter by tag</p>
            <form action="/" method="get">
                <input type="text" name="msg_tag_filter" placeholder="Filter by tag" value="${tagFilter?ifExists}">
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
                            <strong>${m.author.firstName}</strong>
                            <div>
                                <#if m.image??>
                                    <img src="/api/images/${m.image.id}"/>
                                </#if>
                            </div>
                        </li>
                    </#items>
                </ul>
            </#list>
        </div>
    </div>
</@c.page>