<#import "parts/common.ftl" as c>

<@c.page>
    <div id="app">
        <form action="/" method="get">
            <div class="form-inline">
                <div class="form-group col-md-6">
                    <label for="filterId">Filter</label>
                    <input type="text" class="form-control ml-2" id="filterId" name="msg_tag_filter" placeholder="Filter by tag" value="${tagFilter?ifExists}">
                    <button class="btn btn-primary" type="submit">Search</button>
                </div>
            </div>
        </form>
        <div>
            <p class="mt-3 ml-3">
                <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddMessage" role="button" aria-expanded="false" aria-controls="collapseAddMessage">
                    Add new message
                </a>
            </p>
        </div>
        <form action="/" method="post" enctype="multipart/form-data">
            <div id="collapseAddMessage" class="form-group collapse">
                <div class="form-group">
                    <input class="form-control" name="text" placeholder="Write Message Here"/>
                </div>
                <div class="form-group">
                    <input class="form-control" name="tag" placeholder="Write Tag Here"/>
                </div>
                <div class="custom-file">
                    <input id="imageFile" type="file" name="imageFile">
                    <label class="custom-file-label" for="imageFile">Choose Image</label>
                </div>
                <#--<input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                <div class="form-group mt-3">
                    <button class="btn btn-primary" type="submit">Send</button>
                </div>
            </div>
        </form>

        <div class="card-columns">
            <#list messages>
                <#items as m>
                <div class="card my-3">
                    <div>
                        <#if m.image??>
                            <img class="card-img-top" alt="image_view" src="/api/images/${m.image.id}"/>
                        </#if>
                    </div>
                        <div class="ml-2">
                            ${m.text}
                            <i>#${m.tag}</i>
                        </div>
                        <div class="card-footer text-muted">
                            <strong>Author: ${m.author.firstName}</strong>
                        </div>
                    </div>
                </#items>
            </#list>
        </div>
    </div>
</@c.page>