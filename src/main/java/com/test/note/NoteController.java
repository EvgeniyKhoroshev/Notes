package com.test.note;

import com.test.note.domain.Note;
import com.test.note.repos.NoteRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class NoteController {

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
}
