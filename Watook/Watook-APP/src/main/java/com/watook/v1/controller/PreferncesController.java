package com.watook.v1.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Prefernces;
import com.watook.model.Response;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUserMessages;
import com.watook.v1.service.PreferncesService;

/**
 * @author Namdev
 * Date : Dec 11, 2017 9:45:31 PM
 * Description : User Settings and Preferances API's
 */
@RestController
@RequestMapping("/v1/prefernces")
public class PreferncesController {

	@Autowired
	PreferncesService settingService;

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	/**
	 * @param setting
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(@RequestBody Prefernces setting) {
		Response response = null;
		try {

			Prefernces savedSetting = settingService.save(setting);
			if (savedSetting.getSettingId() == null) {
				response = new Response(CommonConstants.FAIL, savedSetting, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, savedSetting, CommonUserMessages.SETTING_SAVED);
			}
		} catch (Exception e) {
			logger.info("Error : " + e);
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Response get(HttpServletRequest request) {
		Response response = null;
		Prefernces savedSetting = null;
		try {

			String userId = request.getParameter("userId");
			savedSetting = settingService.get(userId);
			if (savedSetting.getSettingId() == null) {
				response = new Response(CommonConstants.SUCCESS, savedSetting, CommonConstants.RECORD_NOT_FOUND);
			} else {
				response = new Response(CommonConstants.SUCCESS, savedSetting, null);
			}
		} catch (EmptyResultDataAccessException e) {
			response = new Response(CommonConstants.SUCCESS, new Prefernces(), CommonConstants.RECORD_NOT_FOUND);
		} catch (Exception e) {
			logger.info("Error : " + e);
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}
}
