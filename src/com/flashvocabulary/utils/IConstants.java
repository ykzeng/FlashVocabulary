package com.flashvocabulary.utils;

import java.sql.Date;

public class IConstants {
    public static String LOGIN_PWD_FAILURE = "loginPwdFailure";
    public static String LOGIN_CONN_FAILURE = "loginConnFailure";
    public static String LOGIN_SUCCESS = "loginSuccess";
    public static String REGISTER_SUCCESS = "registerSuccess";
    public static String REGISTER_FAILURE = "registerFailure";
    public static String SESSION_EXPIRED = "sessionExpired";
    public static String ALREADY_EXIST = "alreadyExist";
    public static String ADD_SUCCESS = "addSuccess";
    public static String GET_COLLECTION = "getCollection";
    public static String SAVE_SUCCESS = "saveChangeSuccess";
    public static String FAILURE = "failure";
    public static String LOGOUT = "logout";
    public static String TOCHOOSELIB = "tochoosewordlib";
    
    public static Integer[] reciteIntervals = {1, 2, 4, 7, 15};
	public static Integer defaultGroupLength = 7;
	public static Integer isCheckisTrue = 1;
	public static Integer isCheckisFalse = 0;
	public static Date defaultDate = new Date(0);
	
	
}
