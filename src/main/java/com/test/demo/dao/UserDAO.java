package com.test.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	
}
