package com.revature.ers.handlers.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.util.Dispatcher;
import com.revature.ers.util.HTMLReader;
import com.revature.ers.util.ResourceUrls;

public class ManagerHome implements ManagerHandler {

	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getMethod();
		LOGGER.error(method);
		switch (method.toUpperCase()) {
		case "GET":
			LOGGER.error("Im in");
			processGET(request, response);
			break;
		}
		
	}

	private String getHTML(HttpServletRequest request) {
		HTMLReader htmlReader = new HTMLReader(request, ResourceUrls.getHTMLURLS().get("application"));
		String htmlHeader = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("managerHeader"));
		String htmlMainBody = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("managerHome"));
		String htmlFooter = htmlReader.getNewHTMLResource(request, ResourceUrls.getHTMLURLS().get("applicationFooter"));
		String[] headElements = { "<title>Manager Home</title>" };
		String[] body = { htmlHeader, htmlMainBody, htmlFooter };
		String[] scriptElements = {
				"<script type='text/javascript' src='" + ResourceUrls.getJSURLS().get("managerHeader") + "'></script>",
				"<script type='text/javascript' src='" + ResourceUrls.getJSURLS().get("managerHome") + "'></script>" };

		htmlReader.setHeadElements(headElements).setBodyElement(body).setScriptElements(scriptElements);
		return htmlReader.getHtml().toString();
	}

	private void processGET(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.write(getHTML(request));
		out.flush();
	}
	
}
