package com.test.demo;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.dao.*;
import com.test.demo.model.Note;
import com.test.demo.model.Role;
import com.test.demo.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserDAO userDao;
 
    @Autowired
    private NoteDAO noteDao;
 
    @Autowired
    private RoleDAO roleDao;
    
    @Test
    public void whenFindUserByName_thenReturnUser() {
        // given
        User alex = new User();
        alex.setUsername("alex");
        alex.setPassword("password");
        entityManager.persist(alex);
        entityManager.flush();
     
        // when
        User found = userDao.findByUsername(alex.getUsername());
     
        // then
        assertThat(found.getUsername())
          .isEqualTo(alex.getUsername());
    }
    
    @Test
    public void whenFindNoteById_thenReturnNote() {
        // given
        Note note = new Note();
        note.setName("name");
        note.setText("text");
        
        entityManager.persist(note);
        entityManager.flush();
     
        // when
        Note found = noteDao.findById(note.getId()).get();
     
        // then
        assertThat(found.getName())
          .isEqualTo(note.getName());
    }
    
    @Test
    public void whenFindRoleById_thenReturnRole() {
        // given
        Role role = new Role();
        role.setName("name");
        
        entityManager.persist(role);
        entityManager.flush();
     
        // when
        Role found = roleDao.findById(role.getId()).get();
     
        // then
        assertThat(found.getName())
          .isEqualTo(role.getName());
    }
    
}
