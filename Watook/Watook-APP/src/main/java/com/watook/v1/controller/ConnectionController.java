package com.watook.v1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Response;
import com.watook.model.User;
import com.watook.utils.CommonConstants;
import com.watook.v1.service.ConnectionService;

@RestController
@RequestMapping("/v1/connction")
public class ConnectionController {

	@Autowired
	ConnectionService connectionService;

	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public Response getFriendList(HttpServletRequest request) {
		Response response = null;
		String userId = request.getParameter("userId");
		try {
			List<User> friendList = connectionService.getFriendList(userId);
			if (friendList==null) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, friendList, null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}
}
