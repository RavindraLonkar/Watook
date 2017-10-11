package com.watook.v1.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.watook.model.FBAuthResponse;
import com.watook.model.Response;
import com.watook.model.User;
import com.watook.security.WatookToken;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUserMessages;
import com.watook.v1.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	private static final Logger logger = Logger.getLogger(UserController.class);
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Response getUserList() {
		Response response = null;
								
		try{
						
			List<User> userList = userService.getUserList();
			if (userList.isEmpty()) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			} else {		
				response = new Response(CommonConstants.SUCCESS, userList, null);
			}
		}catch(Exception e){
			logger.info("Error : " + e);
		}		
		return response;
	}
	
	// ------------------- save User ---------------------
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response save(@RequestBody User user) {
		Response response = null;
		
		if(user.getFbToken().equals("") || user.getFbToken() == null)
			return new Response(CommonConstants.FAIL, null, CommonUserMessages.AUTH_FAIL);
		
		FBAuthResponse fBAuthResponse = fbAuthentication(user);
		
		if(fBAuthResponse.getId() == null || !fBAuthResponse.getId().equals(user.getApplicationId()))
			return new Response(CommonConstants.FAIL, null, CommonUserMessages.AUTH_FAIL);
				
		try{
						
		    User userCreated = userService.save(user);
			if (userCreated.getUserId() == null) {
				response = new Response(CommonConstants.FAIL, userCreated, CommonConstants.SYSTEM_ERROR);
			} else {		
				userCreated.setToken(WatookToken.encrypt(user.getFbId(), environment.getRequiredProperty("ENCY_USER_KEY")));
				response = new Response(CommonConstants.SUCCESS, userCreated, CommonUserMessages.USER_SAVED);
			}
		}catch(Exception e){
			logger.info("Error : " + e);
		}		
		return response;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response update(@RequestBody User user) {
		Response response = null;				
		try{
						
		    User userCreated = userService.save(user);
			if (userCreated.getUserId() == null) {
				response = new Response(CommonConstants.FAIL, userCreated, CommonConstants.SYSTEM_ERROR);
			} else {		
				userCreated.setToken(WatookToken.encrypt(user.getFbId(), environment.getRequiredProperty("ENCY_USER_KEY")));
				response = new Response(CommonConstants.SUCCESS, userCreated, CommonUserMessages.USER_SAVED);
			}
		}catch(Exception e){
			logger.info("Error : " + e);
		}		
		return response;
	}
	
	private FBAuthResponse fbAuthentication(User user){
		FBAuthResponse fBAuthResponse = null;
		try {
						
			RestTemplate restTemplate = new RestTemplate();
			String fbAuthUrl = "https://graph.facebook.com/app/?access_token=" + user.getFbToken();			
			fBAuthResponse = restTemplate.getForObject(fbAuthUrl, FBAuthResponse.class);
			fBAuthResponse.setIsAuthSuccess(true);
		} catch (Exception e) {
			logger.info(e);
		}
		return fBAuthResponse;
	}
}

