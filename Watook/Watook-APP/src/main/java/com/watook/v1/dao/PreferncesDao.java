package com.watook.v1.dao;

import com.watook.model.Prefernces;

public interface PreferncesDao {

	Prefernces save(Prefernces setting);

	Prefernces get(String userId);

}
