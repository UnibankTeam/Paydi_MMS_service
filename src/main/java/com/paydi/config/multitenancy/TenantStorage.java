package com.paydi.config.multitenancy;

public class TenantStorage {

	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	private static ThreadLocal<String> currentUID = new ThreadLocal<>();

	private static ThreadLocal<String> currentApiKey = new ThreadLocal<>();

	private static ThreadLocal<String> currentTenantDB = new ThreadLocal<>();
	
	private static ThreadLocal<String> currentTenantUrlServer = new ThreadLocal<>();

	public static void setCurrentApiKey(String apiKey) {
		currentApiKey.set(apiKey);
	}

	public static String getCurrentApiKey() {
		return currentApiKey.get();
	}

	public static void setCurrentTenant(String tenantId) {
		currentTenant.set(tenantId);
	}

	public static String getCurrentTenant() {
		return currentTenant.get();
	}

	public static void setCurrentUID(String uId) {
		currentUID.set(uId);
	}

	public static String getCurrentUID() {
		return currentUID.get();
	}

	public static void clear() {
		currentTenant.remove();
		currentTenantDB.remove();
		currentTenantUrlServer.remove();
		currentUID.remove();
	}

	public static void setCurrentTenantDB(String tenantId) {
		currentTenantDB.set(tenantId);
	}

	public static String getCurrentTenantDB() {
		return currentTenantDB.get();
	}
	
	public static void setCurrentTenantUrlServer(String tenantId) {
		currentTenantUrlServer.set(tenantId);
	}

	public static String getCurrentTenantUrlServer() {
		return currentTenantUrlServer.get();
	}

}
