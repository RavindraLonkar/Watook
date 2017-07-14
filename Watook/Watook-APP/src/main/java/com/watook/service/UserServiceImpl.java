package com.watook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.dao.UserDao;
import com.watook.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> findAllUsers() {		
		return userDao.findAllUsers();
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

}
