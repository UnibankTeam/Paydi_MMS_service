package com.paydi.config.multitenancy;

public class TenantStorage {

	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	private static ThreadLocal<String> currentUID = new ThreadLocal<>();

	private static ThreadLocal<Long> currentAppId = new ThreadLocal<>();

	private static ThreadLocal<String> currentTenantExternalId = new ThreadLocal<>();

	private static ThreadLocal<String> currentTenantDB = new ThreadLocal<>();

	private static ThreadLocal<String> currentTenantUrlServer = new ThreadLocal<>();

	public static void setCurrentAppId(Long appId) {
		currentAppId.set(appId);
	}

	public static Long getCurrentAppId() {
		return currentAppId.get();
	}

	public static String getCurrentTenantExternalId() {
		return currentTenantExternalId.get();
	}

	public static void setCurrentTenantExternalId(String externalId) {
		currentTenantExternalId.set(externalId);
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
