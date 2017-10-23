package com.watook.utils;

public class CommonQueries {
	public static final String SP_GET_USERLIST = "select * from txn_user u left join txn_usertracking ut on ut.userid = u.userid"; // TODO
	public static final String SP_GET_USERSETTING = "SELECT * from txn_usersetting where userId = ?";
}
