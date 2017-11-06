package com.watook.v1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.Prefernces;
import com.watook.utils.CommonProcedures;
import com.watook.utils.CommonQueries;
import com.watook.utils.FieldNamingPolicies;

@Repository
public class PreferncesDaoImpl implements PreferncesDao {

	@Autowired
	DataSource dataSource;

	@Override
	public Prefernces save(Prefernces setting) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String settingJson = gson.toJson(setting);

		String settingId = jdbcTemplate.queryForObject(CommonProcedures.SP_SAVE_USERSETTING,
				new Object[] { settingJson }, String.class);

		Prefernces savedSetting = new Prefernces();
		savedSetting.setSettingId(settingId);

		return savedSetting;
	}

	@Override
	public Prefernces get(String userId) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", Integer.parseInt(userId));
		BeanPropertyRowMapper<Prefernces> rowMapper = BeanPropertyRowMapper.newInstance(Prefernces.class);
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		return namedJdbcTemplate.queryForObject(CommonQueries.SP_GET_USERSETTING, parameters, rowMapper);
	}

}
