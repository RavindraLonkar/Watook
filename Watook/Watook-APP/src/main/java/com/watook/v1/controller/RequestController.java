package com.watook.v1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Request;
import com.watook.model.Response;
import com.watook.model.User;
import com.watook.utils.CommonConstants;
import com.watook.v1.service.RequestService;

@RestController
@RequestMapping("/v1/request")
public class RequestController {

	@Autowired
	RequestService requestService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveRequest(@RequestBody Request request) {
		Response response = null;
		Request status = new Request();
		try {
			status = requestService.saveRequest(request);
			if (status == null) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, status.getRequestId(), null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/list")
	public Response list(HttpServletRequest req) {
		String userId = req.getParameter("userId");
		String requestStatus = req.getParameter("requestStatus");

		Response response = null;
		List<User> requestList = null;
		try {
			requestList = requestService.list(userId, requestStatus);
			if (requestList.isEmpty()) {
				response = new Response(CommonConstants.SUCCESS, requestList, CommonConstants.RECORD_NOT_FOUND);
			} else {
				response = new Response(CommonConstants.SUCCESS, requestList, null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;

	}

	@RequestMapping(value = "/rating",method = RequestMethod.POST)
	public Response RequestRating(HttpServletRequest req) {
		Response response = null;

		String ratingId = req.getParameter("ratingId");
		String ratingTo = req.getParameter("ratingTo");
		Integer status;
		try {
			status = requestService.requestRating(ratingId, ratingTo);
			if (status == null) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, status, null);
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}
}
