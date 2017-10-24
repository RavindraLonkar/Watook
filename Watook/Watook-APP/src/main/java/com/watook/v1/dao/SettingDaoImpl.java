package com.watook.v1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.Setting;
import com.watook.utils.CommonProcedures;
import com.watook.utils.CommonQueries;
import com.watook.utils.FieldNamingPolicies;

@Repository
public class SettingDaoImpl implements SettingDao {
	
	@Autowired
	DataSource dataSource;

	@Override
	public Setting save(Setting setting) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String settingJson = gson.toJson(setting);
		
		String settingId = jdbcTemplate.queryForObject(CommonProcedures.SP_SAVE_USERSETTING, new Object[] { settingJson }, String.class);
		
		Setting savedSetting = new Setting();
		savedSetting.setSettingId(settingId);
		
		return savedSetting;
	}

	@Override
	public Setting get(String userId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.queryForObject(CommonQueries.SP_GET_USERSETTING, new Object[] { Integer.parseInt(userId) }, new BeanPropertyRowMapper<Setting>(Setting.class));
	}

}
