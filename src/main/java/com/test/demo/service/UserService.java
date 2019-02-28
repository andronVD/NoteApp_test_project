package com.test.demo.service;

import com.test.demo.model.User;

public interface UserService {

	void save(User user);

    User findByUsername(String username);
    
}
