package com.test.note.controllers;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private NoteRepos nRepos;

    @PostMapping(value = "/main", params = "show")
    public String show_selected_note(@RequestParam Integer id)
    {
        return "redirect:/note?id="+id.toString();
    }
    @RequestMapping(params = "delete",value = "/main", method = RequestMethod.POST)
    public String delete_note(@RequestParam Integer id)
    {
        Note note = nRepos.findByID(id);
        nRepos.delete(note);
        return "redirect:main";
    }


    @RequestMapping("")
    public String main_page()
    {
        return "redirect:main";
    }
    @GetMapping("/main")
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
        model.addAttribute("filter", filter_text);
        model.addAttribute("notes",notes);
        return "main";
    }
    @PostMapping("/main")
    public String add(@RequestParam String tag, @RequestParam String text, Model model)
    {
        Iterable<Note> notes;
        if (text == "")
        {
            model.addAttribute("report","Введите текст заметки.");
        }
        else {
            model.addAttribute("report","Заметка добавлена.");
            Note note = new Note(text, tag);
            nRepos.save(note);
        }

        notes = nRepos.findAll();
        model.addAttribute("notes", notes);

        return "main";
    }
}
