package Revature.Project1.Util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Revature.Project1.Servlets.LoginServlet;

public class Log4j {
	public static Logger log = Logger.getLogger(LoginServlet.class.getName());
	public static void main(String[] args) {
		log.info("some");
	}
}
