package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.Location;
import com.watook.model.Request;
import com.watook.model.User;
import com.watook.utils.CommonProcedures;
import com.watook.utils.CommonQueries;
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
	
	@Override
	public List<User> list(String userId){
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", Integer.parseInt(userId));
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		List<User> list = namedJdbcTemplate.query(CommonQueries.SP_GET_REQUEST_USERLIST, parameters,  new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setProfileImage(rs.getString("profileimage"));
				Location location = new Location();
				location.setLatitude(rs.getString("latitude"));
				location.setLongitude(rs.getString("longitude"));
				user.setLocation(location);
				
				return user;
			}
		});
		
		return list;  
		
	}
}
