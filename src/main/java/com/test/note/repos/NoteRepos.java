package com.test.note.repos;

import com.test.note.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepos extends CrudRepository<Note, Long> {

}
