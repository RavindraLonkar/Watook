package com.watook.v1.dao;

import java.util.List;

import com.watook.model.User;

public interface ConnectionDao {


	List<User> getFriendList(String userId);
}
