package com.watook.v1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.Location;
import com.watook.utils.FieldNamingPolicies;
@Repository
public class LocationDaoImpl implements LocationDao{

	@Autowired
	DataSource dataSource;
	
	private static final String SP_SAVE_LOCATION = "SELECT * from saveLocation(?)";

	@Override
	public Location saveLocation(Location location) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String locationJson = gson.toJson(location);
		String locationId = jdbcTemplate.queryForObject(SP_SAVE_LOCATION, new Object[] { locationJson }, String.class);
		
		Location newlocation = new Location();
		newlocation.setLocId(locationId);
		
		return newlocation;
	}
}
