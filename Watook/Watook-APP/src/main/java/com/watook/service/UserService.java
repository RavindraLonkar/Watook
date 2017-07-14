package com.watook.service;

import java.util.List;

import com.watook.model.User;

public interface UserService {
	List<User> findAllUsers();

	User save(User user);
}
