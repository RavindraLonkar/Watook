package com.watook.v1.service;

import com.watook.model.Setting;

public interface SettingService {

	Setting save(Setting setting);

	Setting get(String userId);

}
