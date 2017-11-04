package com.watook.utils;

public class CommonQueries {
	public static final String SP_GET_USERLIST = "select * from txn_user u left join txn_usertracking ut on ut.userid = u.userid where u.userid!=:id"; // TODO
	public static final String SP_GET_USERSETTING = "SELECT s.userid,s.distancerange,t.longitude,t.latitude from txn_usersetting s inner join txn_usertracking t on s.userid=t.userid where s.userId = :id";
}
