package com.watook.v1.dao;

import java.util.List;

import com.watook.model.CodeValue;

/**
 * @author namdev.arade
 * @cretaedDate : 26/10/2017
 * @description : This class is used to get master data information.
 *
 */
public interface MasterDao {

	/**
	 * @date 26/10/2017
	 * @param 
	 * @description:This method is for get master of codevalue.
	 * @return: All codevalues.
	 * 
	 */
	List<CodeValue> getCodeValueList();

	/**
	 * @date 27/11/2017
	 * @param 
	 * @description:This method is for get terms and conditions.
	 * @return: All codevalues.
	 * 
	 */
	CodeValue getTermsandConditions();
}
