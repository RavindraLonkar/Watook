package com.watook.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Location;
import com.watook.v1.dao.LocationDao;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;

	@Override
	public Location saveLocation(Location location) {
		return locationDao.saveLocation(location);
	}
	
}
