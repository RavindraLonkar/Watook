package com.watook.v1.service;

import java.util.List;

import com.watook.model.User;

public interface UserService {

	User save(User user);

	List<User> getUserList();
}
