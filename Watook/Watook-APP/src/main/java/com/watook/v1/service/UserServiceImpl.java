package com.watook.v1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Setting;
import com.watook.model.User;
import com.watook.model.UserNearBy;
import com.watook.utils.CalculateDistance;
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
	public List<UserNearBy> getUserNearByList(String userId) {
		List<UserNearBy> userList=new ArrayList<UserNearBy>();
		userList= userDao.getUserNearByList(userId);
		
		Setting userObj= userDao.getUserData(userId);
		//CalculateDistance
		Float distanceRange=Float.parseFloat(userObj.getDistanceRange())*1000;
		Float distance;
		List<UserNearBy> nearByList=new ArrayList<UserNearBy>();
		for(UserNearBy user : userList){
			distance=CalculateDistance.getDistance(Float.parseFloat(userObj.getLongitude()),Float.parseFloat(userObj.getLatitude()),
					Float.parseFloat(user.getLongitude()), Float.parseFloat(user.getLatitude()));
			if(distance<distanceRange){
				user.setDistance((distance).toString());
				nearByList.add(user);
			}			
		}
		
		return nearByList; 
	}

}
