package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.model.Note;
import com.test.demo.service.NoteService;
import com.test.demo.service.impl.NoteServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteServiceIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
    @Autowired
    private NoteService noteService;
    
    @TestConfiguration
    static class NoteServiceImplTestContextConfiguration {
  
        @Bean
        public NoteService noteService() {
            return new NoteServiceImpl();
        }
    }

    @Test
    public void testUpdateNote() {
    	// given
        Note note = new Note();
        note.setName("name");
        note.setText("text");
        
        entityManager.persist(note);
        entityManager.flush();
        
        note.setName("name2");
        note.setText("text2");
        // when
        noteService.updateNote(note);;
     
        // then
        assertThat(note.getName())
          .isEqualTo(note.getName());
    }

    @Test
    public void testDeleteNote() {
    	// given
        Note note = new Note();
        note.setName("name");
        note.setText("text");
        
        entityManager.persist(note);
        entityManager.flush();
        
        // when
        noteService.deleteNote(note);;
     
        // then
        assertThat(noteService.findById(note.getId())).isNull();
    }

    @Test
    public void testFindNoteById() {
    	// given
        Note note = new Note();
        note.setName("name");
        note.setText("text");
        
        entityManager.persist(note);
        entityManager.flush();
        
        // when
        Note found = noteService.findById(note.getId());
     
        // then
        assertThat(found.getName()).isEqualTo(note.getName());
    }

    @Test
    public void testAddNote() {
    	// given
        Note note = new Note();
        note.setName("name");
        note.setText("text");
        
        entityManager.persist(note);
        entityManager.flush();
        
        // when
        noteService.addNote(note);
     
        // then
        assertThat(noteService.findById(note.getId()).getName()).isEqualTo(note.getName());
    }
    
}
