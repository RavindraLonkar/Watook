package com.watook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.dao.UserDao;
import com.watook.model.User;
import com.watook.security.WatookToken;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public Integer save(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

}
