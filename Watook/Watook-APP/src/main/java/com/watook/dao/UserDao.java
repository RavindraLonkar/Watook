package com.watook.dao;

import java.util.List;

import com.watook.model.User;

public interface UserDao {
	List<User> findAllUsers();

	Integer save(User user);
}
