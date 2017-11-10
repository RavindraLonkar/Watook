package com.watook.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.User;
import com.watook.v1.dao.ConnectionDao;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	ConnectionDao connectionDao;
	
	@Override
	public List<User> getFriendList(String userId) {
		return connectionDao.getFriendList(userId);
	}
}
