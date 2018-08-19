<#import "parts/common.ftl" as comm>
<@comm.page>
    <div class="main_body">
        <form method="post" action="/main">
            <input type="text" name="tag" placeholder="Введите название заметки.">
            <input type="text" name="text" placeholder="Введите заметку.">
            <button type="submit">Добавить</button>
            <br>
        </form>
        <b>Список заметок</b>
        <form method="get" action="/main">
            <p>Фильтр</p>
            <input type="text" name="filter_text">
            <button type="submit">Поиск</button>
        </form>
        <br>
        <b>Список заметок:</b>
        <br>
    <#list notes as nt>
        <form class="output">
                <ul>
                    <li> ${nt.id} <#if nt.tag!=""><a href="/note?id=${nt.id}" class="approoved_post">${nt.tag}<#else><a href="/note?id=${nt.id}" class="not_approoved_post">${nt.text}</#if></a></li>
                </ul>
        </form>
    <#else>
    Пока нет ни одной заметки.
    </#list>
    </div>
</@comm.page>