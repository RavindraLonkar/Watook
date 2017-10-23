package com.watook.v1.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.Response;
import com.watook.model.Setting;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUserMessages;
import com.watook.v1.service.SettingService;

@RestController
@RequestMapping("/v1/setting")
public class SettingController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	SettingService settingService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(@RequestBody Setting setting){
		Response response = null;
		try{
						
		    Setting savedSetting = settingService.save(setting);
			if (savedSetting.getSettingId() == null) {
				response = new Response(CommonConstants.FAIL, savedSetting, CommonConstants.SYSTEM_ERROR);
			} else {		
				response = new Response(CommonConstants.SUCCESS, savedSetting, CommonUserMessages.USER_SAVED);
			}
		}catch(Exception e){
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}		
		return response;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Response get(HttpServletRequest request){
		Response response = null;
		try{
			
			String userId = request.getParameter("userId");
		    Setting savedSetting = settingService.get(userId);
			if (savedSetting.getSettingId() == null) {
				response = new Response(CommonConstants.FAIL, savedSetting, CommonConstants.SYSTEM_ERROR);
			} else {		
				response = new Response(CommonConstants.SUCCESS, savedSetting, CommonUserMessages.USER_SAVED);
			}
		}catch(Exception e){
			logger.info("Error : " + e);
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
		}		
		return response;
	}
}
