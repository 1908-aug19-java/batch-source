package com.revature.ers.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

public class HTMLReader {

	private static final HTMLReader htmlReader = new HTMLReader();
	private static String applicationHTML;
	private static String headInjection;
	private static String bodyInjection;
	private static String scriptInjection;
	private static int headInjectionIndex;
	private static int bodyInjectionIndex;
	private static int scriptInjectionIndex;

	private HTMLReader() {
		String filePath = "C:\\Users\\Samuel\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\expense-reimbursment-system\\html\\login.html";
		try {
			applicationHTML = new String(Files.readAllBytes(Paths.get(filePath)));
			headInjection = "<!-- Insert head elements here -->";
			bodyInjection = "<!-- Insert Body here -->";
			scriptInjection = "<!-- Insert external JavaScript here -->";
			headInjectionIndex = applicationHTML.indexOf(headInjection);
			bodyInjectionIndex = applicationHTML.indexOf(headInjectionIndex);
			scriptInjectionIndex = applicationHTML.indexOf(bodyInjectionIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static HTMLReader getHtmlreader() {
		return htmlReader;
	}

	public String getApplicationHTML() {
		return applicationHTML;
	}

	public String setHeadElements(String[] headElements) {
		int injectionIndex = headInjectionIndex + headInjection.length();
		String elements = "";
		for (String s : headElements) {
			elements += s + "\n";
		}
		return applicationHTML.substring(0, injectionIndex) + elements + applicationHTML.substring(injectionIndex);
	}

	public String setBodyElement(String[] bodyElements) {
		int bodyIndex = bodyInjectionIndex + bodyInjection.length();
		String elements = "";
		for (String s : bodyElements) {
			elements += s + "\n";
		}
		return applicationHTML.substring(0, bodyIndex) + elements + applicationHTML.substring(bodyIndex);
	}

	public String setScriptElements(String[] scriptElements) {
		int scriptIndex = scriptInjectionIndex + scriptInjection.length();
		String elements = "";
		for (String s : scriptElements) {
			elements += s + "\n";
		}
		return applicationHTML.substring(0, scriptIndex) + elements + applicationHTML.substring(scriptIndex);
	}

	public String getNewHTMLResource(HttpServletRequest request, String filePath) {
		filePath = request.getServletContext().getRealPath(filePath);
		String resource = "";
		try {
			resource = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
}
