package com.watook.v1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Location;
import com.watook.model.Response;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUserMessages;
import com.watook.v1.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private LocationService locationService;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveLocation(@RequestBody Location location) {
		Response response = null;
		try {
			Location savelocation = locationService.saveLocation(location);
			if (savelocation.getUserId() == null) {
				response = new Response(CommonConstants.FAIL, savelocation, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, savelocation, CommonUserMessages.Location_SAVED);
			}
		} catch (Exception e) {
			logger.info("Error : " + e);
		}
		return response;
	}

}
