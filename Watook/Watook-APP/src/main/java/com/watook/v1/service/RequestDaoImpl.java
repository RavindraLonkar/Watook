package com.watook.v1.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.Request;
import com.watook.utils.CommonProcedures;
import com.watook.utils.FieldNamingPolicies;

@Repository
public class RequestDaoImpl implements RequestDao {

	@Autowired
	DataSource dataSource;

	@Override
	public Request saveRequest(Request request) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String statusJson = gson.toJson(request);

		String requstId = jdbcTemplate.queryForObject(CommonProcedures.SP_USER_REQUEST_STATUS,
				new Object[] { statusJson }, String.class);

		Request status = new Request();
		status.setRequestId(Integer.parseInt(requstId));

		return status;

	}
}
