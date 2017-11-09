package com.watook.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Request;
import com.watook.model.Response;
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
}
