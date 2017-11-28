package com.watook.v1.dao;

import java.util.List;

import com.watook.model.Prefernces;
import com.watook.model.User;
import com.watook.model.UserNearBy;

/**
 * @author Ravindra.Lonkar
 * @cretaedDate : 10/10/2017
 * @description : This class is used to save and get user information.
 *
 */
public interface UserDao {

	/**
	 * @author Ravindra.Lonkar
	 * @date 14/10/2017
	 * @param 
	 * @description:This method is used to save user data.
	 * @return: list of users.
	 * 
	 */
	User save(User user);

	/**
	 * @author Ravindra.Lonkar
	 * @date 12/10/2017
	 * @param 
	 * @description:This method is for get all active user list from system.
	 * @return: list of users.
	 * 
	 */
	List<User> getUserList();

	List<UserNearBy> getUserNearByList(String userId, String agemin, String agemax, String maleinterest,
			String femaleinterest);

	Prefernces getUserData(String userId);

	User getUser(String userId, String requestId);

	String overallRatingCount(String userId);

	String todaysRatingCount(String userId, String Date);

	User getUserProfile(String userId);

}
