package com.test.note.controllers;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private NoteRepos nRepos;

    @RequestMapping("")
    public String main_page(Model model)
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
}
