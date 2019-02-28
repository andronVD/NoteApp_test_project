package com.test.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.dao.NoteDAO;
import com.test.demo.model.Note;
import com.test.demo.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
	
	Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	
	@Autowired
	private NoteDAO noteDao;

	@Override
	public List<Note> getAllNotes() {
		return noteDao.findAll();
	}

	@Override
	public void addNote(Note note) {
		noteDao.save(note);
	}

	@Override
	public void updateNote(Note noteForm) {
		Optional<Note> note = noteDao.findById(noteForm.getId());
		if (note.isPresent()) {
			Note temp = note.get();
			temp.setName(noteForm.getName());
			temp.setText(noteForm.getText());
		} else {
			logger.error("There's no such note with id: ", noteForm.getId());
		}
	}
	
	@Override
	public void deleteNote(Note noteForm) {
		noteDao.delete(noteForm);
	}

	@Override
	public Note findById(Long id) {
		Optional<Note> note = noteDao.findById(id);
		if (note.isPresent()) {
			return note.get();
		}
		logger.error("There's no such note with id: ", id);
		return null;
	}

}
