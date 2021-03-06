package com.watook.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Response;
import com.watook.utils.CommonConstants;

@RestController
public class ErrorController {

	@RequestMapping(value ="/error", method = RequestMethod.GET)
	public Response error() {
		Response response = null;
		response = new Response(CommonConstants.FAIL, null, CommonConstants.INVALID_TOKEN_ERROR);
		return response;
	}
}
