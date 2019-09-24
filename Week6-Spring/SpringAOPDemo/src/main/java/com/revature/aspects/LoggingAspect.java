package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.beans.*)")
	public void logAfter(JoinPoint jp) {
		log.info(jp.getSignature()+" executed successfully");
	}
	
	@AfterThrowing(pointcut="within(com.revature.beans.*)", throwing="ex")
	public void logAfterException(JoinPoint jp, Exception ex) {
		log.error(jp.getSignature()+" threw an exception: "+ ex);
	}
	
	@After("execution(void bearHibernates()) || execution(void setFull(boolean))")
	public void logWithHibernate() {
		log.info("bear attempted to hibernate");
	}
	
	@After("execution(void set*(..))")
	public void logSetter(JoinPoint jp) {
		log.info("setter called ("+jp.getSignature().getName()+")");
	}
	

}
