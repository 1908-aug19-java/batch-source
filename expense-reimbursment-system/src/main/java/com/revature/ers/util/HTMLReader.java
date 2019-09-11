package com.revature.ers.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

public class HTMLReader {

	private static String applicationHTML;
	private static InjectionData headInjection;
	private static InjectionData bodyInjection;
	private static InjectionData scriptInjection;
	private static String headInjectionStart = "<!-- Start: Insert head elements here -->";
	private static String headInjectionEnd = "<!-- End: Insert head elements here -->";
	private static String bodyInjectionStart = "<!-- Start: Insert Body here -->";
	private static String bodyInjectionEnd = "<!-- End: Insert Body here -->";
	private static String scriptInjectionStart = "<!-- Start: Insert external JavaScript here -->";
	private static String scriptInjectionEnd = "<!-- End: Insert external JavaScript here -->";
	private StringBuffer html;

	public HTMLReader(HttpServletRequest request, String filePath) {
		filePath = request.getServletContext().getRealPath(filePath);
		try {
			applicationHTML = new String(Files.readAllBytes(Paths.get(filePath)));
			html = new StringBuffer(applicationHTML);
			headInjection = new InjectionData(applicationHTML.indexOf(headInjectionStart), headInjectionStart.length(),
					applicationHTML.indexOf(headInjectionEnd), headInjectionStart.length());
			bodyInjection = new InjectionData(applicationHTML.indexOf(bodyInjectionStart), bodyInjectionStart.length(),
					applicationHTML.indexOf(bodyInjectionEnd), bodyInjectionEnd.length());
			scriptInjection = new InjectionData(applicationHTML.indexOf(scriptInjectionStart), scriptInjectionStart.length(),
					applicationHTML.indexOf(scriptInjectionEnd), scriptInjectionEnd.length());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getApplicationHTML() {
		return applicationHTML;
	}

	public StringBuffer getHtml() {
		return html;
	}

	public HTMLReader setHeadElements(String[] headElements) {
		String elements = "";
		for (String s : headElements) {
			elements += s + "\n";
		}
		headInjection = new InjectionData(html.indexOf(headInjectionStart), headInjectionStart.length(),
				html.indexOf(headInjectionEnd), headInjectionStart.length());
		 html.insert(headInjection.getStartIndex() + headInjection.getStartLength(), elements);
		 return this;
	}

	public HTMLReader setBodyElement(String[] bodyElements) {		
		String elements = "";
		for (String s : bodyElements) {
			elements += s + "\n";
		}
		bodyInjection = new InjectionData(html.indexOf(bodyInjectionStart), bodyInjectionStart.length(),
				html.indexOf(bodyInjectionEnd), bodyInjectionEnd.length());
		 html.insert(bodyInjection.getStartIndex() + bodyInjection.getStartLength(), elements);
		 return this;
	}

	public HTMLReader setScriptElements(String[] scriptElements) {
		String elements = "";
		for (String s : scriptElements) {
			elements += s + "\n";
		}
		scriptInjection = new InjectionData(html.indexOf(scriptInjectionStart), scriptInjectionStart.length(),
				html.indexOf(scriptInjectionEnd), scriptInjectionEnd.length());
		 html.insert(scriptInjection.getStartIndex() + scriptInjection.getStartLength(), elements);
		 return this;
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

class InjectionData {
	private int startIndex;
	private int startLength;
	private int endIndex;
	private int endLength;

	public InjectionData(int startIndex, int startLength, int endIndex, int endLength) {
		super();
		this.startIndex = startIndex;
		this.startLength = startLength;
		this.endIndex = endIndex;
		this.endLength = endLength;
	}

	@Override
	public String toString() {
		return "Indexes [startIndex=" + startIndex + ", startLength=" + startLength + ", endIndex=" + endIndex
				+ ", endLength=" + endLength + "]";
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStartLength() {
		return startLength;
	}

	public void setStartLength(int startLength) {
		this.startLength = startLength;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getEndLength() {
		return endLength;
	}

	public void setEndLength(int endLength) {
		this.endLength = endLength;
	}

}
