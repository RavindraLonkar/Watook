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
import com.watook.utils.CommonUtilities;
import com.watook.v1.service.LocationService;

/**
 * @author Namdev
 * Dec 11, 2017 9:45:14 PM
 * Description : Users Location API's 
 */
@RestController
@RequestMapping("/v1/location")
public class LocationController {

	@Autowired
	private LocationService locationService;

	private static final Logger logger = Logger.getLogger(UserController.class);
	/**
	 * @param location
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveLocation(@RequestBody Location location) {
		Response response = null;
		try {
			String userid=location.getUserId();
			String latitude=location.getLatitude();
			String longitude=location.getLongitude();
			
			if(userid == null || userid.equals("") || !CommonUtilities.isNumber(userid))
				return new Response(CommonConstants.FAIL, null, CommonUserMessages.INVALID_USERID);
			
			if(latitude == null || latitude.equals("") || !CommonUtilities.isNumeric(latitude))
				return new Response(CommonConstants.FAIL, null, CommonUserMessages.INVALID_LATITUDE);
			
			if(longitude == null || longitude.equals("") || !CommonUtilities.isNumeric(longitude))
				return new Response(CommonConstants.FAIL, null, CommonUserMessages.INVALID_LONGITUDE);
			
			Location locationData = locationService.saveLocation(location);
			if (locationData.getLocId() == null) {
				response = new Response(CommonConstants.FAIL, locationData, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, locationData, CommonUserMessages.LOCATION_SAVED);
			}
		} catch (Exception e) {
			logger.info("Error : " + e);
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}

}
