<!DOCTYPE HTML>
<html>
<head>
    <title>sgfgggggggggggggggg</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<body>
    <div class="main_body">
        <form method="post" action="/main">
            <input type="text" name="tag" placeholder="Введите название заметки.">
            <input type="text" name="text" placeholder="Введите заметку.">
            <button type="submit">Добавить</button>
        </form>
        <b>Список заметок</b>
        <form method="post" action="/filter">
            <p>Фильтр</p>
            <input type="text" name="filter_text">
            <button type="submit">Поиск</button>
        </form>
        <br>
        <b>Список заметок:</b>
        <br>
    {{#notes}}
        <form class="output">
            <ul>
                <li> <a class="approoved_post">{{id}} </a></li>
            </ul>
        </form>
    {{/notes}}
    </div>
</body>
</html>