package com.watook.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.CodeValue;
import com.watook.v1.dao.MasterDao;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterDao masterDao;

	@Override
	public List<CodeValue> getCodeValueList() {
		return masterDao.getCodeValueList();
	}

}
