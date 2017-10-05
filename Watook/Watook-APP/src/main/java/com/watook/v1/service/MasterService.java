package com.watook.v1.service;

import java.util.List;

import com.watook.model.CodeValue;

public interface MasterService {

	List<CodeValue> getCodeValueList(Integer codetypeid);
}
