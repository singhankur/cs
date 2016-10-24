package com.cs.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAdvice {

	@Before("execution(* com.cs.rest.controller.*.*(..))")
	private boolean checkAuthentication(){
		System.out.println("Before Running");
		return true;
		
	}
}
