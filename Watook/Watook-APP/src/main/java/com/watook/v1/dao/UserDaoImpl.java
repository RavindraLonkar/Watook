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
import com.watook.model.Location;
import com.watook.model.User;
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
		        user.setWorkInfo(rs.getString("workinfo"));
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

}
