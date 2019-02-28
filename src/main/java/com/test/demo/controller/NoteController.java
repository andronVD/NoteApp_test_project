package com.test.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.demo.model.Note;
import com.test.demo.service.NoteService;
import com.test.demo.validator.NoteValidator;

@Controller
public class NoteController {
	
	Logger logger = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private NoteValidator noteValidator;

	private List<Note> noteList = new ArrayList<Note>();
	
	@GetMapping({ "/", "/notes" })
	public String notes(Model model) {
		model.addAttribute("notes", noteList);
		return "notes";
	}

	@GetMapping("/addNote")
	public String addNote(Model model) {
		model.addAttribute("noteForm", new Note());
		return "addNote";
	}

	@PostMapping("/addNote")
	public String addNote(@ModelAttribute("noteForm") Note noteForm, BindingResult bindingResult) {
		noteValidator.validate(noteForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addNote";
		}
		noteService.addNote(noteForm);
		updateNoteList();
		logger.info("Added new note with id: ", noteForm.getId());
		return "redirect:/notes";
	}
	
	@GetMapping("/editNote/{id}")
	public String editNote(Model model, @PathVariable("id") Long id) {
		Note note = noteService.findById(id);
		if (note != null) {
			model.addAttribute("noteForm", note);
			return "editNote";
		}
		logger.error("There's no such note with id: ", id);
		return "redirect:/notes";
	}
	
	@PostMapping("/editNote/{id}")
	public String editNote(@ModelAttribute("noteForm") Note noteForm, BindingResult bindingResult) {
		noteValidator.validate(noteForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editNote";
		}
		noteService.updateNote(noteForm);
		updateNoteList();
		logger.info("Update note with id: ", noteForm.getId());
		return "redirect:/notes";
	}
	
	@GetMapping("/deleteNote/{id}")
	public String deleteNote(@PathVariable("id") Long id) {
		Optional<Note> note = noteList.stream().filter(n -> n.getId().equals(id)).findFirst();
		if (note.isPresent()) {
			noteService.deleteNote(note.get());
			updateNoteList();
			logger.info("Deleted note with id: ", id);
		} else {
			logger.error("There's no such note with id: ", id);
		}
		return "redirect:/notes";
	}
	
	private void updateNoteList() {
		noteList.clear();
		noteList.addAll(noteService.getAllNotes());
	}

}
