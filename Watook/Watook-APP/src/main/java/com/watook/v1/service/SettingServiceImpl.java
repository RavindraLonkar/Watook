package com.watook.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Setting;
import com.watook.v1.dao.SettingDao;

@Service
public class SettingServiceImpl implements SettingService{
	
	@Autowired
	private SettingDao settingDao;
	
	@Override
	public Setting save(Setting setting) {
		return settingDao.save(setting);
	}

	@Override
	public Setting get(String userId) {
		return settingDao.get(userId);
	}
	
}
