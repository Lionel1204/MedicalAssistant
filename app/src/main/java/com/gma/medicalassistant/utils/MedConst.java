package com.gma.medicalassistant.utils;

public class MedConst {
    // Const number
    public static final int MIN_INTERVAL_SWITCH_NAVIGATION = 3 * 1000; //second
    // Intent
    public static final String INTENT_ACTION_LOGIN = "ACTION_LOGIN";
    public static final String INTENT_ACTION_CALL_DOCTOR = "ACTION_CALL_DOCTOR";
    public static final String INTENT_ACTION_MEASUREMENT = "ACTION_MEASUREMENT";
    public static final String INTENT_ACTION_PLAN = "ACTION_PLAN";
    public static final String INTENT_ACTION_CHECK_RESULT = "ACTION_CHECK_RESULT";
    public static final String INTENT_ACTION_HEART_RATE = "ACTION_HEART_RATE";


    // Request Code
    public static final int LOGIN_REQUEST_CODE = 10001;
    public static final int CALL_DOCTOR_REQUEST_CODE = 10021;
    public static final int MEASUREMENT_REQUEST_CODE = 10031;
    public static final int PLAN_REQUEST_CODE = 10041;
    public static final int CHECK_REQUEST_CODE = 10051;
    public static final int HEART_RATE_REQUEST_CODE = 10061;


    // Mock Login info
    public static final String MOCK_LOGIN_INFO1 = "P0104:18621373333";
    public static final String MOCK_LOGIN_INFO2 = "test1:13012341234";
}
