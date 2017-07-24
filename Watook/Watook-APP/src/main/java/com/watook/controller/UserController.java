package com.watook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Response;
import com.watook.model.User;
import com.watook.security.WatookToken;
import com.watook.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	private static final Logger logger = Logger.getLogger(UserController.class);

	// ------------------- save User ---------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response save(@RequestBody User user) {
		Response response = null;
		try{
			if(user == null)
				return null;
			
			int userCreated = userService.save(user);
			if (userCreated == 0) {
				response = new Response("fail", null, "", "");
			} else {				
				String encryptKey = WatookToken.encrypt(user.getFbID(), environment.getRequiredProperty("ENCY_USER_KEY"));
				response = new Response("success", 1, "", encryptKey);
			}
		}catch(Exception e){
			logger.info("Error : " + e);
		}		
		return response;
	}
}

