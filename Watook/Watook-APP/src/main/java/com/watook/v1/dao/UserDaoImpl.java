package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.watook.model.Location;
import com.watook.model.Setting;
import com.watook.model.User;
import com.watook.model.UserNearBy;
import com.watook.utils.CommonProcedures;
import com.watook.utils.CommonQueries;
import com.watook.utils.FieldNamingPolicies;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<User> getUserList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		return jdbcTemplate.query(CommonQueries.SP_GET_USERLIST, new RowMapper<User>() {
		    @Override
		    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        User user = new User();
		        user.setUserId(rs.getString("userid"));
		        user.setFbId(rs.getString("fbid"));
		        user.setLastName(rs.getString("lastname"));
		        user.setMiddleName(rs.getString("middlename"));
		        user.setFirstName(rs.getString("firstname"));
		        user.setGenderId(rs.getString("genderid"));
		        user.setContactMobile(rs.getString("contactmobile"));
		        user.setContactMobile2(rs.getString("contactmobile2"));
		        user.setEmailId(rs.getString("emailid"));
		        user.setAdvertiseId(rs.getString("advertiseid"));
		        user.setAboutYou(rs.getString("aboutyou"));
		        user.setWorkEmployer(rs.getString("workemployer"));
		        user.setWorkLocation(rs.getString("worklocation"));
		        user.setWorkPosition(rs.getString("workposition"));
		        user.setStatusInfo(rs.getString("statusinfo"));
		        user.setFbImages(rs.getString("fbimages"));
		        user.setProfileImage(rs.getString("profileimage"));
		        user.setFireBaseToken(rs.getString("firebasetoken"));
		        Location location = new Location();
		        location.setLatitude(rs.getString("latitude"));
		        location.setLongitude(rs.getString("longitude"));

		        user.setLocation(location);

		        return user;
		    }
		});
		
		/*return jdbcTemplate.query(SP_GET_USERLIST, new Object[] {},
				new RowMapperResultSetExtractor<User>(new BeanPropertyRowMapper<User>(User.class)));*/
	}

	@Override
	public User save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String userJson = gson.toJson(user);
		
		String userid = jdbcTemplate.queryForObject(CommonProcedures.SP_SAVE_USER, new Object[] { userJson }, String.class);
		
		User newUser = new User();
		newUser.setUserId(userid);
		
		return newUser;
	/*	return jdbcTemplate.queryForObject(SP_SAVE_USER, new Object[] { userJson }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setFbId(rs.getString("fbid"));
				return user;
			}
		});*/

	}

	@Override
	public Setting getUserData(String userId) {
		
		/*JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return (Setting) jdbcTemplate.queryForObject(CommonQueries.SP_GET_USERSETTING, new Object[] {Integer.parseInt(userId)}, new BeanPropertyRowMapper<Setting>(Setting.class));
	*/
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", Integer.parseInt(userId));
		BeanPropertyRowMapper<Setting> rowMapper = BeanPropertyRowMapper.newInstance(Setting.class);
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//namedJdbcTemplate.setFetchSize(FETCH_SIZE);
		Setting list = namedJdbcTemplate.queryForObject(CommonQueries.SP_GET_USERSETTING, parameters, rowMapper);
		return list;
	}
	
	
	
	@Override
	public List<UserNearBy> getUserNearByList(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", Integer.parseInt(userId));
		BeanPropertyRowMapper<UserNearBy> rowMapper = BeanPropertyRowMapper.newInstance(UserNearBy.class);
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//namedJdbcTemplate.setFetchSize(FETCH_SIZE);
		List<UserNearBy> list = namedJdbcTemplate.query(CommonQueries.SP_GET_USERLIST, parameters, rowMapper);
		return list;
		
		/*
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		return jdbcTemplate.query(CommonQueries.SP_GET_USERLIST, new RowMapper<UserNearBy>() {
		    @Override
		    public UserNearBy mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	UserNearBy user = new UserNearBy();
		        user.setUserId(rs.getString("userid"));
		        user.setFbId(rs.getString("fbid"));
		        user.setLastName(rs.getString("lastname"));
		        user.setMiddleName(rs.getString("middlename"));
		        user.setFirstName(rs.getString("firstname"));
		        user.setGenderId(rs.getString("genderid"));
		        user.setContactMobile(rs.getString("contactmobile"));
		        user.setContactMobile2(rs.getString("contactmobile2"));
		        user.setEmailId(rs.getString("emailid"));
		        user.setAdvertiseId(rs.getString("advertiseid"));
		        user.setAboutYou(rs.getString("aboutyou"));
		        user.setWorkEmployer(rs.getString("workemployer"));
		        user.setWorkLocation(rs.getString("worklocation"));
		        user.setWorkPosition(rs.getString("workposition"));
		        user.setStatusInfo(rs.getString("statusinfo"));
		        user.setFbImages(rs.getString("fbimages"));
		        user.setProfileImage(rs.getString("profileimage"));
		       
		        Location location = new Location();
		        location.setLatitude(rs.getString("latitude"));
		        location.setLongitude(rs.getString("longitude"));

		        user.setLocation(location);

		        return user;
		    }
		});
		
	*/}

}
