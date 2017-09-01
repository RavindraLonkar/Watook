package com.watook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.watook.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;

	private static final String SP_GET_USERLIST = "select * from txn_user";
	private static final String SP_SAVE_USER = "SELECT jsoninsert('txn_user',?)";

	@Override
	public List<User> getUserList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.query(SP_GET_USERLIST, new Object[] {}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserID(rs.getString("userId"));
				user.setFirstName(rs.getString("firstName"));
				user.setFirstName(rs.getString("firstName"));
				return user;
			}
		});
	}

	@Override
	public Integer save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		Gson gson = new Gson();
		String userJson = gson.toJson(user);
		return jdbcTemplate.queryForObject(SP_SAVE_USER, new Object[] {userJson}, Integer.class);
				
	}

}
