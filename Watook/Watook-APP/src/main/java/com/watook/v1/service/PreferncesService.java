package com.watook.v1.service;

import com.watook.model.Prefernces;

public interface PreferncesService {

	Prefernces save(Prefernces setting);

	Prefernces get(String userId);

}
