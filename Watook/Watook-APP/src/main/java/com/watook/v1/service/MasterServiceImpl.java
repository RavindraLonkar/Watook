package com.watook.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.CodeValue;
import com.watook.v1.dao.MasterDao;

/**
 * @author namdev.arade
 * @cretaedDate : 26/10/2017
 * @description : This class is used to get master data information.  
 *
 */
@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterDao masterDao;

	/**
	 * @date 26/10/2017
	 * @param 
	 * @description:This method is for get master of codevalue.
	 * @return: All codevalues.
	 * 
	 */
	@Override
	public List<CodeValue> getCodeValueList() {
		return masterDao.getCodeValueList();
	}

	/**
	 * @date 27/11/2017
	 * @param 
	 * @description:This method is for get terms and conditions.
	 * @return: All codevalues.
	 * 
	 */
	@Override
	public CodeValue getTermsandConditions() {
		return masterDao.getTermsandConditions();
	}

}
