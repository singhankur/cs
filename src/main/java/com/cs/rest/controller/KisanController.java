package com.cs.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs.Constants.Status;
import com.cs.mongo.model.Kisan;
import com.cs.rest.services.KisanService;
import com.cs.rest.services.SessionManagementService;

@CrossOrigin
@RestController
public class KisanController {
	
	@Autowired
	KisanService kisanService;
	@Autowired
	private SessionManagementService sessionManagementService;
	
	
			@RequestMapping(value = "/createKisan", method = RequestMethod.POST)
			public ResponseEntity<String> addkisan(@RequestBody Kisan kisan) {
				String sttus = "Invalid";
				 HttpHeaders httpHeaders = new HttpHeaders();
			    if (kisan != null) {
			    	String sessionResponse = sessionManagementService.validateSession(kisan.getSession_id());
			    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
			    	    return new ResponseEntity<String>(Status.sessionInvalid, httpHeaders,HttpStatus.UNAUTHORIZED);
					
			    	
			    	sttus = kisanService.addKisan(kisan);
			    }
			   
	
			    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
			}
}
