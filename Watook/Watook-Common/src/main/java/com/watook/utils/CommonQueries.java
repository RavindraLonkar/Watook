package com.watook.utils;

public class CommonQueries {
	public static final String SP_GET_USERLIST = "select * from txn_user u left join txn_usertracking ut on ut.userid = u.userid";
	public static final String SP_GET_USERSETTING = "SELECT s.userid,s.distancerange,t.longitude,t.latitude from txn_usersetting s inner join txn_usertracking t on s.userid=t.userid where s.userId = :id";

	public static final String SP_GET_USERLIST_NEARBY = "select u.userid,u.firstname,u.lastname,u.statusinfo,u.profileimage,u.fbimages,u.firebasetoken,u.age,u.dob,"
			+ "u.genderid,u.aboutyou,u.workemployer,u.worklocation,u.workposition,ut.latitude,ut.longitude,COALESCE(ur.reqstatus,0) "
			+ "as requestStatus from txn_user u left join txn_usertracking ut on ut.userid = u.userid	"
			+ "left join txn_userrequest ur on u.userid = ur.requestby where u.userid!=:id and u.isactive=1";

	public static final String SP_GET_USER = "select * from txn_user u left join txn_usertracking ut "
			+ "on ut.userid = u.userid left join txn_userrequest ur "
			+ "on ((ur.requestby=:id and ur.requestto=:requestId) or (ur.requestby=:requestId and ur.requestto=:id)) "
			+ "where u.userid=:id";

}
