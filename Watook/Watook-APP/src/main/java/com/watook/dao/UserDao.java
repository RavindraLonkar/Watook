package com.watook.dao;

import java.util.List;

import com.watook.model.User;

public interface UserDao {

	User save(User user);

	List<User> getUserList();
}
