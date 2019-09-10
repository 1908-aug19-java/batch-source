package com.revature.ers.util;

import java.util.HashMap;
import java.util.Map;

public class ResourceUrls {

	private static final Map<String, String> HTMLURLS = new HashMap<>();
	private static final Map<String, String> CSSURLS = new HashMap<>();
	private static final Map<String, String> JSURLS = new HashMap<>();
	private static ResourceUrls resourceUrls = new ResourceUrls();

	public static ResourceUrls getResourceUrls() {
		return resourceUrls;
	}

	public static Map<String, String> getHTMLURLS() {
		Map<String, String> shallowCopy = new HashMap<>();
		shallowCopy.putAll(HTMLURLS);
		System.out.println(shallowCopy);
		return shallowCopy;
	}

	public static Map<String, String> getCSSURLS() {
		Map<String, String> shallowCopy = new HashMap<>();
		shallowCopy.putAll(CSSURLS);
		return shallowCopy;
	}

	public static Map<String, String> getJSURLS() {
		Map<String, String> shallowCopy = new HashMap<>();
		shallowCopy.putAll(JSURLS);
		return shallowCopy;
	}

	private ResourceUrls() {
		// Main html resources
		HTMLURLS.put("login", "/html/login.html");
		HTMLURLS.put("application", "/html/application.html");
		HTMLURLS.put("applicationFooter", "/html/mainFooter.html");
		HTMLURLS.put("applicationHeader", "/html/mainHeader.html");

		// Employee html resources
		HTMLURLS.put("employeeHeader", "/html/employee/employeeHeader.html");
		HTMLURLS.put("employeeHome", "/html/employee/employeeHome.html");
		HTMLURLS.put("employeeProfile", "/html/employee/employeeProfile.html");
		HTMLURLS.put("employeeReimbursement", "/html/employee/employeeReimbursement.html");

		// Manager html Resources
		HTMLURLS.put("allEmployees", "/html/manager/allEmployees.html");
		HTMLURLS.put("managerEmployee", "/html/manager/managerEmployee.html");
		HTMLURLS.put("managerHeader", "/html/manager/managerHeader.html");
		HTMLURLS.put("managerHome", "/html/manager/managerHome.html");
		HTMLURLS.put("registerEmployee", "/html/manager/registerEmployee.html");

		// Main js resources
		JSURLS.put("login", "/ers/js/login.js");
		JSURLS.put("application", "/ers/js/application.js");

		// Employee js resources
		JSURLS.put("employeeHome", "/ers/js/employee/employeeHome.js");
		JSURLS.put("employeeProfile", "/ers/js/employee/employeeProfile.js");
		JSURLS.put("employeeReimbursement", "/ers/js/employee/employeeReimbursement.js");

		// Manager js Resources
		JSURLS.put("allEmployees", "/ers/js/manager/allEmployees.js");
		JSURLS.put("managerEmployee", "/ers/js/manager/managerEmployee.js");
		JSURLS.put("managerHome", "/ers/js/manager/managerHome.js");
		JSURLS.put("registerEmployee", "/ers/js/manager/registerEmployee.js");
	}

}
