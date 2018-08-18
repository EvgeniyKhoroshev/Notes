package com.test.note.repos;

import com.test.note.domain.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepos extends CrudRepository<Note, Long>
{
    @Query(value = "select * from note where tag like :tag_or_text or text like :tag_or_text", nativeQuery = true)
    List<Note> findByTagOrText(@Param("tag_or_text") String tag_or_text);
}
