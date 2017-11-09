package com.watook.v1.service;

import java.util.List;

import com.watook.model.User;
import com.watook.model.UserNearBy;

public interface UserService {

	User save(User user);

	List<User> getUserList();

	List<UserNearBy> getUserNearByList(String userId);

	User getUser(String userId,String requestId);
}
