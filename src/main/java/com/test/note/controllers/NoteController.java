package com.test.note.controllers;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {
    @Autowired
    private NoteRepos nRepos;
    @GetMapping("/note")
    public String showNote(@RequestParam(required = false) String report,
                           @RequestParam Integer id, Model model) {
        Note note = nRepos.findByID(id);
        model.addAttribute("note", note);
        return "note";
    }
    @RequestMapping(params = "delete",value = "/note", method = RequestMethod.POST)
    public String delete_note(@RequestParam Integer id, Model model)
    {
        Note note = nRepos.findByID(id);
        nRepos.delete(note);
        return "redirect:main";
    }
    @PostMapping(value = "/note", params = "update")
    public String update_note(@RequestParam(required = false) Integer id,
                              @RequestParam(required = false) String tag,
                              @RequestParam(required = false) String text, Model model)
    {
        String report = "Изменения сохранены.";
        Note note = nRepos.findByID(id);
        String buf = "note";
        if (!text.isEmpty())
        {
            note.setTag(tag);
            note.setText(text);
            nRepos.save(note);
        }
        else
        {
            report = "Текст заметки не может быть пустым.";
            model.addAttribute("note", note);
            model.addAttribute("report", report);
            return buf;
        }

        return "redirect:main";
    }


    @RequestMapping("/add_note")
    public String show_add_note()
    {
        return "add_note";
    }
    @PostMapping("/add_note")
    public String add_note(@RequestParam String tag, @RequestParam String text, Model model)
    {
        String report;
        if (text != "")
        {
            Note note = new Note(text,tag);
            nRepos.save(note);
            model.addAttribute("note",  note);
            String buf = "/note?id=" + note.getId().toString();
            return "redirect:"+buf;
        }
        else
            report = "Введите текст заметки.";
        model.addAttribute("report", report);
        return "add_note";
    }


}
