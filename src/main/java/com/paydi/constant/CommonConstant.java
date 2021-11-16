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
	public static final int API_CODE_ERRER_COMMON = 500;
	public static final String API_MESSAGE_SUCCESS = "success";
	public static final String API_MESSAGE_FAIL = "failure";
	public static final String API_MESSAGE_UNAUTHORIZE = "Unauthorize with Core";
	public static final String API_MESSAGE_FAIL_666 = "666";
	
	public static final String AMOUNT_TRANSACTION_RANGE_ACCEPTABLE = "BIAS_ROUND_TXN_AMOUNT";
	
	public static final String FEE_OFFICE_MAPPING_TYPE_MIN_RATE = "MIN_RATE";
	public static final String FEE_OFFICE_MAPPING_TYPE_COGS_RATE = "COGS_RATE";
	
	public static final String SYSTEM_BASE_DIR_VOUCHER_PDF_DOWNLOAD = "VOUCHER_PATH_PARENT" ;
	
	public static final String SYSTEM_BASE_DIR_INVOICE_UPLOAD_TEMP = "INVOICE_UPLOAD_TEMP" ;
	
	public static final String SYSTEM_BASE_DIR_TRANSACTION_EXPORT_TEMP = "TRANSACTION_EXPORT_TEMP" ;
	
	public static final String SYS_REPORT_TRANSACTION_IN = "report_transaction-in.xlsx";
	public static final String SYS_REPORT_TRANSACTION_LESS_IN = "report_transaction_less-in.xlsx";
	public static final String SYS_REPORT_TRANSACTION_OUT = "report_transaction.xlsx";
	
	public static final String SYS_PARTNER_REPORT_TRANSACTION_IN = "partner_report_transaction-in.xlsx";
	public static final String SYS_PARTNER_REPORT_TRANSACTION_OUT = "partner_report_transaction.xlsx";
	
	public static final String TRANSACTION_NOT_FOUND = "TRANSACTION_NOT_FOUND" ;
	
	public static final String MASTER_ECPAY_CLIENT = "ECPAY_CLIENT";
	public static final String MASTER_ECPAY_SAVING_ACCOUNT = "ECPAY_SAVING_ACCOUNT";
	public static final String MASTER_HEAD_CLIENT = "HEAD_CLIENT";
	public static final String MASTER_HEAD_SAVING_ACCOUNT_IPNL = "HEAD_SAVING_ACCOUNT_IPNL";
	public static final String MASTER_HEAD_SAVING_ACCOUNT_IEAP = "HEAD_SAVING_ACCOUNT_IEAP";
	
	public static final String MGM_AMOUNT = "MGM_AMOUNT";
	
	
	public static final String API_MESSAGE_AUTHENNOTFOUND = "AUTHENTICATION NOT FOUND!!!";
	public static final String API_MESSAGE_PERMISSTIONDENIED = "PERMISSTION DENIED!!!";
	public static final String SYS_REPORT_TEMPALRE_IN = "Dai_ly_report daily-in.xlsx";
	public static final String SYS_REPORT_TEMPALRE_OUT = "Dai_ly_report daily.xlsx";
	public static final String SYS_REPORT_TEMPALRE_PNL_IN = "report-PNL-in.xlsx";
	public static final String SYS_REPORT_TEMPALRE_PNL_OUT = "report-PNL.xlsx";
	public static final String SYS_REPORT_TEMPALRE_BillingMaker_INT = "Billing_maker-in.xlsx";
	public static final String SYS_REPORT_TEMPALRE_BillingMaker_OUT = "Billing_maker.xlsx";
	
	public static final String SYS_REPORT_BATCH_TXN_IN = "qry_batch_bill-in.xlsx";
	public static final String SYS_REPORT_BATCH_TXN_OUT = "qry_batch_bill.xlsx";
	
	public static final String SYS_REPORT_CARD_DUEDAY_IN = "daily_card_due_day-in.xlsx";
	public static final String SYS_REPORT_CARD_DUEDAY_OUT = "daily_card_due_day.xlsx";
	
	public static final String SYS_REPORT_CARD_EXPIRED_DATE_IN = "card_expired_in_3months-in.xlsx";
	public static final String SYS_REPORT_CARD_EXPIRED_DATE_OUT = "card_expired_in_3months.xlsx";
	 
	public static final String SYS_REPORT_MONTHLY_IN = "Monthly_report_daily-in.xlsx";
	public static final String SYS_REPORT_MONTHLY_OUT = "Monthly_report_daily.xlsx";


	public static final String TABLE_INVOICE_UPLOAD_PREFIX = "iftb_invoice_";
	public static final String TENANT_HEADER = "core-tenant";
	public static final String UID_HEADER = "uid";
	public static final String API_KEY_HEADER = "api-key";
	public static final String TENANT_DB_KEY = "paydi.server.db.ternant.";
	public static final String TENANT_URL_KEY = "paydi.server.url.ternant.";
	public static final String HOME_URL_IC_APP = "https://kiotthe.com/ic-app";

}	
