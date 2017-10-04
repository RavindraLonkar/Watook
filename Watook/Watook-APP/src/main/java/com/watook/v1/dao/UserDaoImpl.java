package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.User;
import com.watook.utils.FieldNamingPolicies;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;

	private static final String SP_GET_USERLIST = "select * from txn_user"; // TODO
																			// :
	private static final String SP_SAVE_USER = "SELECT * from saveUser(?)";

	@Override
	public List<User> getUserList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.query(SP_GET_USERLIST, new Object[] {},
				new RowMapperResultSetExtractor<User>(new BeanPropertyRowMapper<User>(User.class)));
	}

	@Override
	public User save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String userJson = gson.toJson(user);

		return jdbcTemplate.queryForObject(SP_SAVE_USER, new Object[] { userJson }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserID(rs.getString("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setFbID(rs.getString("fbid"));
				return user;
			}
		});

	}

}
