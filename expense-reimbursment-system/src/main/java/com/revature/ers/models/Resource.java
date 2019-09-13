package com.revature.ers.models;

public class Resource {

	private String html;
	private String javascript;
	private String css;
	public Resource() {
		super();
	}
	public Resource(String html, String javascript, String css) {
		super();
		this.html = html;
		this.javascript = javascript;
		this.css = css;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getJavascript() {
		return javascript;
	}
	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	@Override
	public String toString() {
		return "Resource [html=" + (html != null) + ", javascript=" + (javascript != null) + ", css=" + (css != null) + "]";
	}
	
	
}
