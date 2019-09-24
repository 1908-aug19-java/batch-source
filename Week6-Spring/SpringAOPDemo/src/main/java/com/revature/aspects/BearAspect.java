package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.revature.beans.Bear;

@Component
@Aspect
public class BearAspect {
	
	@Before("execution(void bearHibernates())")
	public void stockedUpForWinter(JoinPoint jp) {
		Bear b = (Bear) jp.getTarget();
		if(b.isFull()) {
			System.out.println("Bear is stocked up and ready for winter");
		} else {
			System.out.println("Bear will be hungry when he wakes up");
		}
	}
	
	@Around("execution(void wakeBear())")
	public void wakeBearAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Bear b = (Bear) pjp.getTarget();
		if(b.isFull()) {
			System.out.println("proceeding with waking the bear - have your bear spray ready");
			pjp.proceed();
		} else {
			System.out.println("bear is hungry, best not to wake our bear");
		}
	}

}
