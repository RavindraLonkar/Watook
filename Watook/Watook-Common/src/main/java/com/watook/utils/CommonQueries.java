package com.watook.utils;

public class CommonQueries {
	public static final String SP_GET_USER_PROFILE = "select * from txn_user where userid=:id and isactive=1";

	public static final String SP_GET_USERLIST = "select * from txn_user u left join txn_usertracking ut on ut.userid = u.userid where u.isactive=1";

	public static final String SP_GET_USERSETTING = "SELECT s.userid,s.distancerange,t.longitude,t.latitude,s.agemin,s.agemax,"
			+ "COALESCE(s.femaleinterest,0) femaleinterest, COALESCE(s.maleinterest,0) maleinterest from txn_usersetting s inner join txn_usertracking t on s.userid=t.userid where s.userId = :id";

	public static final String SP_GET_USERLIST_NEARBY = "select u.userid,u.firstname,u.lastname,u.statusinfo,u.profileimage,u.firebasetoken,u.age,u.dob,ut.latitude,ut.longitude from txn_user u"
			+ " inner join txn_usertracking ut on ut.userid = u.userid"
			+ "	where u.userid!=:userId	and (u.age>=:agemin and u.age<=:agemax) and u.genderid in(:maleinterest,:femaleinterest) and u.isactive=1 and u.discoverable=1 ";

	public static final String SP_GET_USER = "select * from txn_user u left join txn_userrequest ur"
			+ "  on ((ur.requestby=:id and ur.requestto=:requestId) or (ur.requestby=:requestId and ur.requestto=:id)) and ur.isactive=1"
			+ "  inner join txn_usertracking ut 	on ut.userid = u.userid"
			+ "  where u.userid=:requestId   and  u.isactive=1";

	public static final String SP_GET_REQUEST_USERLIST = "select u.userid,u.firstname,u.lastname,u.age,u.profileimage,ut.latitude,ut.longitude,t.requestby"
			+ " from txn_user u inner join txn_userrequest t"
			+ " on (u.userid=t.requestby and t.requestby!=:userId) or (u.userid=t.requestto and t.requestto!=:userId) "
			+ " inner join txn_usertracking ut on ut.userid=u.userid where (t.requestto=:userId or t.requestby=:userId)  and t.rejectattemtcount<=3 and t.reqstatus=:requestStatus "
			+ " and  u.isactive=1 and t.isactive=1";

	public static final String SP_GET_FRIEND_LIST = "select * from txn_userrequest ur inner join txn_user u "
			+ " on (u.userid=ur.requestby and ur.requestby!=:id) or (u.userid=ur.requestto and ur.requestto!=:id) "
			+ "inner join txn_usertracking ut on ut.userid = u.userid "
			+ " where ur.reqstatus=501 and (ur.requestby=:id or ur.requestto=:id) and  u.isactive=1";

	public static final String SP_GET_TODAYS_RATING = "select count(*) todaysRating from txn_profilerating  where ratingto=:userId and lastmodifieddate::timestamp::date=to_date(:date,'YYYY-MM-DD')";

	public static final String SP_GET_OVERALL_RATING = "select count(*) overallRating from txn_profilerating  where ratingto=:userId";

	public static final String SP_GET_CODEVLAUE_LIST = "select type.codetype,codevalue.* from cfg_codevalue codevalue inner join cfg_codetype type on codevalue.codetypeid=type.codetypeid";

	public static final String SP_GET_TERMS_AND_CONDITION = "select * from cfg_codevalue where codevalueid=801 and codetypeid=8";
}
