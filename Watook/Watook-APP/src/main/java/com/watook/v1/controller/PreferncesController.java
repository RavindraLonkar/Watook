package com.watook.v1.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Prefernces;
import com.watook.model.Response;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUserMessages;
import com.watook.v1.service.PreferncesService;

@RestController
@RequestMapping("/v1/prefernces")
public class PreferncesController {

	@Autowired
	PreferncesService settingService;

	private static final Logger logger = Logger.getLogger(UserController.class);

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

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Response get(HttpServletRequest request) {
		Response response = null;
		try {

			String userId = request.getParameter("userId");
			Prefernces savedSetting = settingService.get(userId);
			if (savedSetting.getSettingId() == null) {
				response = new Response(CommonConstants.FAIL, savedSetting, CommonConstants.SYSTEM_ERROR);
			} else {
				response = new Response(CommonConstants.SUCCESS, savedSetting, null);
			}
		} catch (Exception e) {
			logger.info("Error : " + e);
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}
		return response;
	}
}
