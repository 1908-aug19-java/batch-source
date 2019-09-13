package com.revature.ers.util;

import java.util.HashMap;
import java.util.Map;

public class ResourceUrls {

	private static final Map<String, String> HTMLURLS = new HashMap<>();
	private static final Map<String, String> CSSURLS = new HashMap<>();
	private static final Map<String, String> JSURLS = new HashMap<>();
	private static final Map<String, String> DIRECTORYURLS = new HashMap<>();
	private static ResourceUrls resourceUrls = new ResourceUrls();

	public static ResourceUrls getResourceUrls() {
		return resourceUrls;
	}

	public static Map<String, String> getHTMLURLS() {
		Map<String, String> shallowCopy = new HashMap<>();
		shallowCopy.putAll(HTMLURLS);
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

	public static Map<String, String> getDIRECTORYURLS() {
		Map<String, String> shallowCopy = new HashMap<>();
		shallowCopy.putAll(DIRECTORYURLS);
		return shallowCopy;
	}

	private ResourceUrls() {
		DIRECTORYURLS.put("HTML", "static/html");
		DIRECTORYURLS.put("JS", "static/js");
		DIRECTORYURLS.put("CSS", "static/css");
		
		// Main html resources
		HTMLURLS.put("login", "static/html/login.html");
		HTMLURLS.put("application", "static/html/application.html");
		HTMLURLS.put("applicationFooter", "static/html/applicationFooter.html");
		HTMLURLS.put("applicationHeader", "static/html/applicationHeader.html");

		// Employee html resources
		HTMLURLS.put("employeeHeader", "static/html/employee/employeeHeader.html");
		HTMLURLS.put("employeeHome", "static/html/employee/employeeHome.html");
		HTMLURLS.put("employeeProfile", "static/html/employee/employeeProfile.html");
		HTMLURLS.put("employeeReimbursement", "static/html/employee/employeeReimbursement.html");

		// Manager html Resources
		HTMLURLS.put("allEmployees", "static/html/manager/allEmployees.html");
		HTMLURLS.put("managerEmployee", "static/html/manager/managerEmployee.html");
		HTMLURLS.put("managerHeader", "static/html/manager/managerHeader.html");
		HTMLURLS.put("managerHome", "static/html/manager/managerHome.html");
		HTMLURLS.put("registerEmployee", "static/html/manager/registerEmployee.html");

		// Main js resources
		JSURLS.put("login", "static/js/login.js");
		JSURLS.put("application", "static/js/application.js");

		// Employee js resources
		JSURLS.put("employeeHome", "static/js/employee/employeeHome.js");
		JSURLS.put("employeeProfile", "static/js/employee/employeeProfile.js");
		JSURLS.put("employeeReimbursement", "static/js/employee/employeeReimbursement.js");

		// Manager js Resources
		JSURLS.put("allEmployees", "static/js/manager/allEmployees.js");
		JSURLS.put("managerEmployee", "static/js/manager/managerEmployee.js");
		JSURLS.put("managerHeader", "static/js/manager/managerHeader.js");
		JSURLS.put("managerHome", "static/js/manager/managerHome.js");
		JSURLS.put("registerEmployee", "static/js/manager/registerEmployee.js");
	}

}
