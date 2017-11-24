package com.watook.v1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.CodeValue;
import com.watook.model.Response;
import com.watook.utils.CommonConstants;
import com.watook.v1.service.MasterService;

@RestController
@RequestMapping("v1/master")
public class MasterController {

	private static final Logger logger = Logger.getLogger(MasterController.class);

	@Autowired
	MasterService masterService;

	@RequestMapping(value = "/codevalue", method = RequestMethod.GET)
	public Response getCodeValueList(HttpServletRequest request) {
		Response response = null;
		try {
				List<CodeValue> codeValue = masterService.getCodeValueList();
				if (codeValue.isEmpty()) {
					response = new Response(CommonConstants.FAIL, null, CommonConstants.RECORD_NOT_FOUND);
				} else {
					response = new Response(CommonConstants.SUCCESS, codeValue, null);
				}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			logger.info("Error In Master Controller getCodeValueList" + e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/termsAndCodition", method = RequestMethod.GET)
	public Response getTermsandConditions(HttpServletRequest request) {
		Response response = null;
		try {
			CodeValue codeValue = masterService.getTermsandConditions();
				if (codeValue==null) {
					response = new Response(CommonConstants.FAIL, null, CommonConstants.RECORD_NOT_FOUND);
				} else {
					response = new Response(CommonConstants.SUCCESS, codeValue, null);
				}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			logger.info("Error In Master Controller getTermsandConditions: " + e.getMessage());
		}
		return response;
	}
}
