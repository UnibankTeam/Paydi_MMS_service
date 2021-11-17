package com.paydi.constant;

public class CommonConstant {

	public static final String FILE_CONFIG_NAME = "ServiceConfig.properties";
	public static final String FILE_UPLOAD_DOCUMENT = "document_temp";
	public static final String DB_STATUS_AVAILABLE = "RECORD_INITATED";
	public static final String DB_STATUS_NOT_AVAILABLE = "RECORD_COMPLETED";
	public static final String DB_STATUS_DELETE = "RECORD_DELETED";
	public static final String DB_STATUS_PENDING = "RECORD_PENDING";
	public static final String DB_STATUS_FAILED = "RECORD_FAILED";

	public static final String FILE_UPLOAD_RECODE_STATUS_AVAILABLE = "A";
	public static final String FILE_UPLOAD_RECODE_STATUS_PENDING = "P";
	public static final String FILE_UPLOAD_RECODE_STATUS_FAILED = "F";
	public static final String FILE_UPLOAD_RECODE_STATUS_COMPLETED = "C";

	public static final int API_CODE_SUCCESS = 200;
	public static final int API_CODE_FAIL = 404;
	public static final int API_CODE_UNAUTHORIZE = 401;
	public static final int API_CODE_INTERNAL_SERVER_ERROR = 500;
	public static final int API_CODE_NOT_FOUND = 400;
	public static final int API_CODE_PARTNER_NOT_FOUND = 403;
	public static final int API_CODE_DUPLICATE = 402;

	public static final String API_MESSAGE_SUCCESS = "success";
	public static final String API_MESSAGE_FAIL = "failure";
	public static final String API_MESSAGE_UNAUTHORIZE = "Unauthorize with Core";
	public static final String API_MESSAGE_FAIL_666 = "666";

	public static final String TENANT_HEADER = "core-tenant";
	public static final String UID_HEADER = "uid";
	public static final String API_KEY_HEADER = "api-key";
	public static final String TENANT_DB_KEY = "paydi.server.db.ternant.";
	public static final String TENANT_URL_KEY = "paydi.server.url.ternant.";

}
