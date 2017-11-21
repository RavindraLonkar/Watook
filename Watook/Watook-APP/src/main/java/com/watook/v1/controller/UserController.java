package com.watook.v1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.watook.model.UserNearBy;
import com.watook.security.WatookToken;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUserMessages;
import com.watook.v1.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public Response getUserAuth(HttpServletRequest request) {

		try {
			String applicationId = request.getParameter("applicationId");
			String fbToken = request.getParameter("fbToken");

			if (fbToken == null || fbToken.equals(""))
				return new Response(CommonConstants.FAIL, null, CommonUserMessages.AUTH_FAIL);

			FBAuthResponse fBAuthResponse = fbAuthentication(fbToken);

			if (fBAuthResponse.getId() == null || !fBAuthResponse.getId().equals(applicationId))
				return new Response(CommonConstants.FAIL, null, CommonUserMessages.AUTH_FAIL);

			String token = WatookToken.encrypt(applicationId + "|" + environment.getRequiredProperty("MATCH_USER_KEY"),
					environment.getRequiredProperty("ENCY_USER_KEY"));

			return new Response(CommonConstants.SUCCESS, token, CommonUserMessages.AUTH_SUCCESS);

		} catch (Exception e) {
			return new Response(CommonConstants.FAIL, null, CommonUserMessages.AUTH_FAIL);
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Response getUserList() {
		Response response = null;

		try {
			List<User> userList = userService.getUserList();
			if (userList.isEmpty()) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, userList, null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}

	// ------------------- save User ---------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response save(@RequestBody User user) {
		Response response = null;
		try {

			User userCreated = userService.save(user);
			if (userCreated.getUserId() == null) {
				response = new Response(CommonConstants.FAIL, userCreated, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, userCreated, CommonUserMessages.USER_SAVED);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}

	private FBAuthResponse fbAuthentication(String fbToken) {
		FBAuthResponse fBAuthResponse = null;
		try {

			RestTemplate restTemplate = new RestTemplate();
			String fbAuthUrl = "https://graph.facebook.com/app/?access_token=" + fbToken;
			fBAuthResponse = restTemplate.getForObject(fbAuthUrl, FBAuthResponse.class);
			fBAuthResponse.setIsAuthSuccess(true);
		} catch (Exception e) {
			logger.info(e);
		}
		return fBAuthResponse;
	}

	@RequestMapping(value = "/nearbylist", method = RequestMethod.GET)
	public Response getUserNearByList(HttpServletRequest request) {
		Response response = null;
		String userId = request.getParameter("userId");
		try {
			List<UserNearBy> userList = userService.getUserNearByList(userId);
			if (userList==null || userList.isEmpty()) {
				response = new Response(CommonConstants.SUCCESS, userList, CommonConstants.RECORD_NOT_FOUND);
			} else {
				response = new Response(CommonConstants.SUCCESS, userList, null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}

	// get user data with location and request
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Response getUser(HttpServletRequest request) {
		Response response = null;
		String userId = request.getParameter("userId");
		String requestId = request.getParameter("requestId");
		try {
			User user = userService.getUser(userId,requestId);
			if (user == null) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, user, null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}
	
	// get user data
		@RequestMapping(value = "/profile", method = RequestMethod.GET)
		public Response getUserProfile(HttpServletRequest request) {
			Response response = null;
			String userId = request.getParameter("userId");
			try {
				User user = userService.getUserProfile(userId);
				if (user == null) {
					response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
				} else {
					response = new Response(CommonConstants.SUCCESS, user, null);
				}
			} catch (Exception e) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			}
			return response;
		}
}
