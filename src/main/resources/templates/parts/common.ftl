<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/static/theme1/css/application.css" />
    <script src="/static/theme1/js/js.js"></script>
    <meta charset="UTF-8">
    <title>Заметки</title>
</head>
<body>

<div class="main_header">
    Заметки </div>
<div class="main_body">
    <#nested>
</div>
    </body>
</html>
</#macro>
<#macro add_form>
    <form method="post">
        <p>Добавить заметку</p>
        <input type="text" name="tag" placeholder="Введите название заметки.">
        <br>
        <input type="text" name="text" placeholder="Введите заметку.">
        <button type="submit">
        Добавить</button>
        <br>
        <#if report?has_content>
            <b>${report}</b>
        </#if>
    </form>
</#macro>

<#macro note_list>
    <div class="navigation" >
        <form method="get">
            <p>Фильтр</p>
            <input type="text" name="filter_text"  <#if filter?has_content> value="${filter}"</#if>>
            <button type="submit">Поиск</button>
        </form>
    </div>
        <h1>Список заметок:</h1>
        <br>
        <#list notes as nt>
            <div class="single_note">
                <form method="post">
                    <input type="hidden" name="id" value="${nt.id}"/>
                    <#if nt.tag!="">
                    <p class="label">Название заметки : </p> <br>
                    <a href="/note?id=${nt.id}">${nt.tag}
                    <#else>
                        <p class="label">Текст заметки : </p> <br>
                        <a href="/note?id=${nt.id}">${nt.text}</#if></a>
                    <button type="submit" name="show">Показать заметку</button>
                    <button type="submit" name="delete" onclick="return confirm('Вы уверены, что хотите удалить заметку?')">
                        Удалить</button>
                </form>
            <br>
            <#else>
            <h1>            Пока нет ни одной заметки.
            </h1>
             </div>
        </#list>
</#macro>


<#macro show_note>
    <div class="single_note">
        <form method="post">
            <input type="hidden" name="id" value="${note.id}"/>
            <p class="label">Название заметки №${note.id}: </p> <br>
            <input name="tag" value="${note.tag}"/>
            <br>
            <p class="label">Текст заметки : </p> <br>
            <textarea name="text">${note.text}</textarea>
            <br>
            <button type="submit" name="update">Сохранить изменения</button>
            <button type="submit" name="delete" onclick="return confirm('Вы уверены, что хотите удалить заметку?')">
                Удалить</button>
            <br>
            <a href="/main">Перейти на главную</a>
            <#if report?has_content>
            <b>${report}</b>
        </#if>
        <br>
        </form>

    </div>
</#macro>