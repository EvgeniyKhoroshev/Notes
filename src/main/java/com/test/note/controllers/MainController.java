package com.test.note.controllers;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private NoteRepos nRepos;

    @PostMapping("/add_note")
    public String add_note(@RequestParam String tag, @RequestParam String text, Model model)
    {
        String report;
        if (text != "")
        {
            Note note = new Note(text,tag);
            nRepos.save(note);
            report = "Заметка успешно добавлена.";
        }
        else
            report = "Введите текст заметки.";
        model.addAttribute("report", report);
        return "add_note";
    }

    @GetMapping("/note")
    public String note(@RequestParam(required = false) Integer id, Model model) {
        Note note = nRepos.findByID(id);
        model.addAttribute("note", note);
        return "note";
    }
    @GetMapping("")
    public String main(@RequestParam(required = false, defaultValue = "") String filter_text, Model model)
    {

        Iterable<Note> notes = nRepos.findAll();
        String buf = "%";
        if (!filter_text.isEmpty()) {
            buf += filter_text + "%";
            notes = nRepos.findByTagOrText(buf);
        }
        else
        {
            notes = nRepos.findAll();
        }
        model.addAttribute("notes",notes);
        return "main";
    }
    @PostMapping("/main")
    public String add(@RequestParam String tag, @RequestParam String text, Model model)
    {
        Boolean error = false;
        if (text == "")
            error = true;
        else {
            Note note = new Note(text, tag);
            nRepos.save(note);
        }
            Iterable<Note> notes = nRepos.findAll();
        model.addAttribute("notes", notes);

        return "main";
    }
//    @PostMapping ("filter") // Filter realization
//    public String filter(@RequestParam String filter_text, Map<String, Object> model)
//    {
//        Iterable<Note> notes;
//        String buf = "%";
//        if(!filter_text.isEmpty())
//        {
//            buf += filter_text+"%";
//            notes = nRepos.findByTagOrText(buf);
//        }
//        else
//        {
//            notes = nRepos.findAll();
//        }
//        model.put("notes", notes);
//        return "main";
//    }
}
