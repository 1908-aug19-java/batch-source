package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static Logger log = Logger.getRootLogger();

	@AfterReturning(pointcut = "within(com.revature.beans.*)", returning = "result")
	public void logResult(JoinPoint p, double result) {
		Object[] o = p.getArgs();
		String name = p.getSignature().getName();
		String method = "";
		switch (name) {
		case "add":
			method = " + ";
			break;
		case "subtract":
			method = " - ";
			break;
		case "multiply":
			method = " * ";
			break;
		case "divide":
			method = " / ";
			break;
		}
		log.info(p.getSignature().getName() + " Was executed, " + o[0] + method + o[1] + " = " + result);
	}

	@AfterThrowing(pointcut = "within(com.revature.beans.*)", throwing = "ex")
	public void logAfterException(JoinPoint jp, Exception ex) {
		log.error(jp.getSignature().getName() + " threw an exception: " + ex);
	}

}
