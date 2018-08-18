package com.test.note.controllers;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    private NoteRepos nRepos;
    @GetMapping("/note")
    public String note(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "note";
    }

    @GetMapping
    public String main(String name, Map<String, Object> model)
    {
        Iterable<Note> notes = nRepos.findAll();
        model.put("notes", notes);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String tag, @RequestParam String text, Map<String, Object> model)
    {
        Note note = new Note(text, tag);
        nRepos.save(note);

        Iterable<Note> notes = nRepos.findAll();
        model.put("notes", notes);

        return "main";
    }
    @PostMapping ("filter")
    public String filter(@RequestParam String filter_text, Map<String, Object> model)
    {
        Iterable<Note> notes;
//        Map<int, String> dataset = new Map<int, String>();
        String buf = "%";
        if(!filter_text.isEmpty())
        {
            buf += filter_text+"%";
            notes = nRepos.findByTagOrText(buf);
        }
        else
        {
            notes = nRepos.findAll();
        }
        model.put("notes", notes);

        return "main";
    }
}
