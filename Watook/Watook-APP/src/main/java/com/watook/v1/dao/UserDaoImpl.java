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
import com.watook.model.Prefernces;
import com.watook.model.Request;
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
				user.setDob(rs.getString("dob"));
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
	}

	@Override
	public Prefernces getUserData(String userId) {
		BeanPropertyRowMapper<Prefernces> rowMapper = BeanPropertyRowMapper.newInstance(Prefernces.class);
		Prefernces list = (Prefernces) getData(userId, rowMapper, CommonQueries.SP_GET_USERSETTING);
		return list;
	}

	@Override
	public User save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicies.lowerCaseFields()).create();
		String userJson = gson.toJson(user);

		String userid = jdbcTemplate.queryForObject(CommonProcedures.SP_SAVE_USER, new Object[] { userJson },
				String.class);

		User newUser = new User();
		newUser.setUserId(userid);

		return newUser;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getData(String parameter, BeanPropertyRowMapper rowMapper, String query) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", Integer.parseInt(parameter));
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		return namedJdbcTemplate.queryForObject(query, parameters, rowMapper);
	}

	@Override
	public List<UserNearBy> getUserNearByList(String userId, String agemin, String agemax, String maleinterest,
			String femaleinterest) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", Integer.parseInt(userId));
		parameters.addValue("agemin", Integer.parseInt(agemin));
		parameters.addValue("agemax", Integer.parseInt(agemax));
		parameters.addValue("maleinterest", Integer.parseInt(maleinterest));
		parameters.addValue("femaleinterest", Integer.parseInt(femaleinterest));
		BeanPropertyRowMapper<UserNearBy> rowMapper = BeanPropertyRowMapper.newInstance(UserNearBy.class);
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		List<UserNearBy> list = namedJdbcTemplate.query(CommonQueries.SP_GET_USERLIST_NEARBY, parameters, rowMapper);
		return list;
	}

	@Override
	public User getUser(String userId, String requestId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", Integer.parseInt(userId));
		parameters.addValue("requestId", Integer.parseInt(requestId));
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		User list = namedJdbcTemplate.queryForObject(CommonQueries.SP_GET_USER, parameters, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setLastName(rs.getString("lastname"));
				user.setMiddleName(rs.getString("middlename"));
				user.setFirstName(rs.getString("firstname"));
				user.setGenderId(rs.getString("genderid"));
				user.setDob(rs.getString("dob"));
				user.setContactMobile(rs.getString("contactmobile"));
				user.setContactMobile2(rs.getString("contactmobile2"));
				user.setEmailId(rs.getString("emailid"));
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
				Request requeststatus = new Request();
				requeststatus.setRequestId(rs.getInt("id"));
				requeststatus.setRequestBy(rs.getInt("requestby"));
				requeststatus.setRequestTo(rs.getInt("requestto"));
				requeststatus.setReqstatus(rs.getInt("reqstatus"));
				requeststatus.setRejectattemtcount(rs.getInt("rejectattemtcount"));
				user.setRequest(requeststatus);
				return user;
			}
		});

		return list;
	}

	@Override
	public String overallRatingCount(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", Integer.parseInt(userId));
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		return namedJdbcTemplate.queryForObject(CommonQueries.SP_GET_OVERALL_RATING, parameters, String.class);
	}

	@Override
	public String todaysRatingCount(String userId, String Date) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", Integer.parseInt(userId));
		parameters.addValue("date", Date);
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		return namedJdbcTemplate.queryForObject(CommonQueries.SP_GET_TODAYS_RATING, parameters, String.class);
	}
}