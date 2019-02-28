package com.test.demo.service;

import java.util.List;

import com.test.demo.model.Note;

public interface NoteService {

	List<Note> getAllNotes();
	
	void addNote(Note note);

	void updateNote(Note noteForm);

	void deleteNote(Note noteForm);

	Note findById(Long id);
	
}
