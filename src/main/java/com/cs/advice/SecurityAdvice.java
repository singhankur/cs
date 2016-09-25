package com.cs.advice;

import org.aspectj.lang.annotation.Before;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class SecurityAdvice {

	@Before(value = "")
	private boolean checkAuthentication(){
		System.out.println("Before Running");
		return true;
		
	}
}
