package com.watook.v1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Prefernces;
import com.watook.model.User;
import com.watook.model.UserNearBy;
import com.watook.utils.CalculateDistance;
import com.watook.utils.CommonUtilities;
import com.watook.v1.dao.UserDao;

/**
 * @author Ravindra.Lonkar
 * @cretaedDate : 10/10/2017
 * @description : This class is used to save and get user information.
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * @author Ravindra.Lonkar
	 * @date 14/10/2017
	 * @param 
	 * @description:This method is used to save user data.
	 * @return: list of users.
	 * 
	 */
	@Override
	public User save(User user) {
		Integer age = 0;
		try {
			age = CommonUtilities.getAge(user.getDob());
		} catch (Exception e) {
			System.out.println("Exception in age calculate:" + e.getMessage());
		}
		user.setAge(age.toString());
		return userDao.save(user);
	}

	/**
	 * @author Ravindra.Lonkar
	 * @date 12/10/2017
	 * @param 
	 * @description:This method is for get all active user list from system.
	 * @return: list of users.
	 * 
	 */
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public List<UserNearBy> getUserNearByList(String userId) {
		List<UserNearBy> userList = new ArrayList<UserNearBy>();
		Prefernces userObj = userDao.getUserData(userId);
		userList = userDao.getUserNearByList(userId, userObj.getAgeMin(), userObj.getAgeMax(),
				userObj.getMaleInterest(), userObj.getFemaleInterest());

		// CalculateDistance
		// distance range in meter
		Double distanceRange = Double.parseDouble(userObj.getDistanceRange());
		Double distance;
		List<UserNearBy> nearByList = new ArrayList<UserNearBy>();
		for (UserNearBy user : userList) {
			distance = CalculateDistance.getDistance(Float.parseFloat(userObj.getLatitude()),
					Float.parseFloat(userObj.getLongitude()), Float.parseFloat(user.getLatitude()),
					Float.parseFloat(user.getLongitude()));
			if (distance < distanceRange) {
				user.setDistance((distance).toString());
				nearByList.add(user);
			}
		}
		return nearByList;
	}

	@Override
	public User getUser(String userId, String requestId) {
		User data = new User();
		data = userDao.getUser(userId, requestId);
		data.setOverallRating(userDao.overallRatingCount(requestId));
		String date = CommonUtilities.CurrentDate();
		data.setTodaysRating(userDao.todaysRatingCount(requestId, date));
		return data;
	}

	@Override
	public User getUserProfile(String userId) {
		User data = new User();
		data = userDao.getUserProfile(userId);
		data.setOverallRating(userDao.overallRatingCount(userId));
		String date = CommonUtilities.CurrentDate();
		data.setTodaysRating(userDao.todaysRatingCount(userId, date));
		return data;
	}

}
