package com.watook.service;

import java.util.List;

import com.watook.model.User;

public interface UserService {

	Integer save(User user);

	List<User> getUserList();
}
