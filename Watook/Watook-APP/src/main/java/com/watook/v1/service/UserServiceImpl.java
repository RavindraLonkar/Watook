package com.watook.v1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.User;
import com.watook.model.UserNearBy;
import com.watook.v1.dao.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	@Override
	public List<UserNearBy> getUserNearByList(int userId) {
		UserNearBy currentUser=new UserNearBy();
		
		List<UserNearBy> userList=new ArrayList<UserNearBy>();
		userList= userDao.getUserNearByList();
		return null; 
	}

}
