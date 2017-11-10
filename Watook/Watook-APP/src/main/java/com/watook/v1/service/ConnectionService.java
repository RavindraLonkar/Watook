package com.watook.v1.service;

import java.util.List;

import com.watook.model.User;

public interface ConnectionService {

	List<User> getFriendList(String userId);

}
