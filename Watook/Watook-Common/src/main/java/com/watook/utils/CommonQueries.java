package com.watook.utils;

public class CommonQueries {
	public static final String SP_GET_USERLIST = "select * from txn_user u left join txn_usertracking ut on ut.userid = u.userid";
	public static final String SP_GET_USERSETTING = "SELECT s.userid,s.distancerange,t.longitude,t.latitude,s.agemin,s.agemax,"
			+ "COALESCE(s.femaleinterest,0) femaleinterest, COALESCE(s.maleinterest,0) maleinterest from txn_usersetting s inner join txn_usertracking t on s.userid=t.userid where s.userId = :id";

	public static final String SP_GET_USERLIST_NEARBY = "select u.userid,u.firstname,u.lastname,u.statusinfo,u.profileimage,u.firebasetoken,u.age,u.dob,ut.latitude,ut.longitude from txn_user u"
			+ " inner join txn_usertracking ut on ut.userid = u.userid"
			+ "	where u.userid!=:userId	and (u.age>=:agemin and u.age<=:agemax) and u.genderid in(:maleinterest,:femaleinterest) and u.isactive=1 ";

	public static final String SP_GET_USER = "select * from txn_user u left join txn_usertracking ut "
			+ "on ut.userid = u.userid left join txn_userrequest ur "
			+ "on ((ur.requestby=:id and ur.requestto=:requestId) or (ur.requestby=:requestId and ur.requestto=:id)) "
			+ "where u.userid=:requestId " + "order by ur.lastmodifieddate desc limit 1";

	public static final String SP_GET_REQUEST_USERLIST = "select u.userid,u.firstname,u.lastname,u.age,u.profileimage,ut.latitude,ut.longitude,t.requestby"
			+ " from txn_user u inner join txn_userrequest t"
			+ " on (u.userid=t.requestby and t.requestby!=:userId) or (u.userid=t.requestto and t.requestto!=:userId)"
			+ "	 inner join txn_usertracking ut on ut.userid=u.userid where (t.requestto=:userId or t.requestby=:userId)  and t.rejectattemtcount<=3 and t.reqstatus=503";

	public static final String SP_GET_FRIEND_LIST = "select * from txn_userrequest ur inner join txn_user u "
			+ " on (u.userid=ur.requestby and ur.requestby!=:id) or (u.userid=ur.requestto and ur.requestto!=:id) "
			+ "inner join txn_usertracking ut on ut.userid = u.userid "
			+ " where ur.reqstatus=501 and (ur.requestby=:id or ur.requestto=:id) ";

}
