package com.watook.v1.dao;

import com.watook.model.Setting;

public interface SettingDao {

	Setting save(Setting setting);

	Setting get(String userId);

}
