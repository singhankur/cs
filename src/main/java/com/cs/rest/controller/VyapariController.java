package com.cs.rest.controller;

import java.util.Set;

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
import com.cs.mongo.model.Vyapari;
import com.cs.rest.services.SessionManagementService;
import com.cs.rest.services.VyapariServices;

@RestController
public class VyapariController {

	

	@Autowired
	VyapariServices vyapariServices;
	@Autowired
	private SessionManagementService sessionManagementService;
	
			@CrossOrigin
			@RequestMapping(value = "/createVyapari", method = RequestMethod.POST)
			public ResponseEntity<String> update(@RequestBody Vyapari vyapari) {
				String sttus = "Invalid";
				HttpHeaders httpHeaders = new HttpHeaders();
			    if (vyapari != null) {
			    	String sessionResponse = sessionManagementService.validateSession(vyapari.getSession_id());
			    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
			    		 return new ResponseEntity<String>(Status.sessionInvalid, httpHeaders,HttpStatus.UNAUTHORIZED);
			    	sttus = vyapariServices.addvypari(vyapari);
			    }
			  
			    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
			}
			
			@CrossOrigin
			@RequestMapping(value = "/getEnlistedVyaparis", method = RequestMethod.GET)
			public Set<String>  getEnlistedVyaparis() {
			    return vyapariServices.getAllVypari();
			}
			
			
}
