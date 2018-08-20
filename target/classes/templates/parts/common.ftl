<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/resources/theme1/css/application.css" />
    <script src="/resources/theme1/js/js.js"></script>
    <meta charset="UTF-8">
    <title>Заметки</title>
</head>
<body>
        <#nested>
</body>
</html>
</#macro>

<#macro add_form>
    <form method="post">
        <input type="text" name="tag" placeholder="Введите название заметки.">
        <br>
        <input type="text" name="text" placeholder="Введите заметку.">
        <button type="submit">Добавить</button>
        <br>
        <#if report?has_content>
            <b>${report}</b>
        </#if>
        <br>
        <a href="/main">На главную</a>
    </form>
</#macro>

<#macro note_list>
    <div class="navigation">
        <form method="get">
            <p>Фильтр</p>
            <input type="text" name="filter_text">
            <button type="submit">Поиск</button>
        </form>
    </div>
    <div class="main_body">
        <a href="/add_note">Добавить заметку</a>
        <br>
        <#if report?has_content>
        <b>${report}</b>
        </#if>
        <br>
        <b>Список заметок:</b>
        <br>
        <#list notes as nt>
        <form class="output" method="post">
            <ul>
                <li> ${nt.id}
                    <#if nt.tag!=""><a href="/note?id=${nt.id}" class="approoved_post">${nt.tag}
                    <#else><a href="/note?id=${nt.id}" class="not_approoved_post">${nt.text}</#if></a>
                    <input type="hidden" name="id" value="${nt.id}"/>
                    <button type="submit" name="delete" onclick="return confirm('are u shure?')">Удалить</button>
                </li>
            </ul>
        </form>
        <#else>
        Пока нет ни одной заметки.
    </#list>
    </div>
</#macro>


<#macro show_note>
    <div class="single_note">
        <form method="post">
            <input type="hidden" name="id" value="${note.id}"/>
            <p>Название заметки №${note.id}: </p>
            <input name="tag" value="${note.tag}"/>
            <br>
            <p>Текст заметки : </p>
            <textarea name="text">${note.text}</textarea>
            <br>
            <button type="submit" name="update">Сохранить изменения</button>
            <button type="submit" name="delete" onclick="return confirm('are u shure?')">Удалить</button>
        </form>
        <#if report?has_content>
        <b>${report}</b>
        </#if>
        <br>
        <a href="/main">Перейти на главную</a>
    </div>
</#macro>