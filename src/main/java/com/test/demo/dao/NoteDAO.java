package com.test.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.model.Note;

@Repository
public interface NoteDAO extends JpaRepository<Note, Long>{

}
