package com.watook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.watook.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	private static final String SP_FIND_ALL_USERS = "{CALL sp_getAllUsers()}";

	@Override
	public List<User> findAllUsers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.query(SP_FIND_ALL_USERS, new Object[] {}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("name"));
				return user;
			}
		});
	}

	@Override
	public User save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.queryForObject(SP_FIND_ALL_USERS, new Object[] {user}, User.class);
	}

}
