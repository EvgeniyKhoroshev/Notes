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
    @Query(value = "select * from note where id = :id", nativeQuery = true)
    Note findByID(@Param("id") Integer id);
    @Query(value = "update note set tag = :tag, text = :text where id = :id; " +
            "select * from note where id = :id", nativeQuery = true)
    Note nativeUpdate(@Param("id")Integer id,@Param("tag") String tag,@Param("text") String text);
    @Query(value = "delete from note where id = :id", nativeQuery = true)
    void deleteByID(@Param("id")Integer id);
}
