package com.watook.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Prefernces;
import com.watook.v1.dao.PreferncesDao;

@Service
public class PreferncesServiceImpl implements PreferncesService{
	
	@Autowired
	private PreferncesDao settingDao;
	
	@Override
	public Prefernces save(Prefernces setting) {
		return settingDao.save(setting);
	}

	@Override
	public Prefernces get(String userId) {
		return settingDao.get(userId);
	}
	
}
