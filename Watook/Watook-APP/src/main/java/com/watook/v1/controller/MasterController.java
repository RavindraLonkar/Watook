package com.watook.v1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.watook.model.CodeValue;
import com.watook.model.Response;
import com.watook.utils.CommonConstants;
import com.watook.utils.CommonUtilities;
import com.watook.v1.service.MasterService;

@RestController
@RequestMapping("v1/master")
public class MasterController {

	private static final Logger logger = Logger.getLogger(MasterController.class);

	@Autowired
	MasterService masterService;

	@RequestMapping(value = "/codevalue", method = RequestMethod.GET)
	public Response getCodeValueList(HttpServletRequest request) {
		String codetypeid = request.getParameter("codetypeid");
		Response response = null;
		try {
			if (CommonUtilities.ValidateIntegerData(codetypeid).equals(false)) {
				response = new Response(CommonConstants.FAIL, null, CommonConstants.MASTER_INPUT_DATA_ERROR_MSG);
			} else {
				List<CodeValue> codeValue = masterService.getCodeValueList(Integer.parseInt(codetypeid));
				if (codeValue.isEmpty()) {
					response = new Response(CommonConstants.FAIL, null, CommonConstants.RECORD_NOT_FOUND);
				} else {
					response = new Response(CommonConstants.SUCCESS, codeValue, null);
				}
			}
		} catch (Exception e) {
			response = new Response(CommonConstants.FAIL, null, CommonConstants.SYSTEM_ERROR);
			logger.info("Error In Master Controller getCodeValueList" + e.getMessage());
		}
		return response;
	}
}
