package com.test.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.model.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {

}
