package com.watook.v1.dao;

import java.util.List;

import com.watook.model.User;
import com.watook.model.UserNearBy;

public interface UserDao {

	User save(User user);

	List<User> getUserList();

	List<UserNearBy> getUserNearByList();
}
