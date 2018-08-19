package com.test.note.controllers;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    @Autowired
    private NoteRepos nRepos;
    @GetMapping("/note")
    public String showNote(@RequestParam Integer id, Model model) {
        Note note = nRepos.findByID(id);
        model.addAttribute("note", note);
        return "note";
    }

    @RequestMapping("/add_note")
    public String add_note(Model model)
    {
        String report;
//        if (text != "")
//        { @RequestParam String tag, @RequestParam String text,
//            Note note = new Note(text,tag);
//            nRepos.save(note);
//            report = "Заметка успешно добавлена.";
//        }
//        else
//            report = "Введите текст заметки.";
//        model.addAttribute("report", report);
        return "add_note";
    }
}
