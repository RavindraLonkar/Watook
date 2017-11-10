package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.watook.model.Location;
import com.watook.model.Request;
import com.watook.model.User;
import com.watook.utils.CommonQueries;

@Repository
public class ConnectionDaoImpl implements ConnectionDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<User> getFriendList(String userId) {
		List<User> friendList=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", Integer.parseInt(userId));
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		friendList= namedJdbcTemplate.query(CommonQueries.SP_GET_FRIEND_LIST, parameters, new RowMapper<User>() {
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

		return friendList;
	}
}
