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

import com.cs.mongo.model.Kisan;
import com.cs.rest.services.KisanService;

@CrossOrigin
@RestController
public class KisanController {
	
	@Autowired
	KisanService kisanService;
	
	
			@RequestMapping(value = "/createKisan", method = RequestMethod.POST)
			public ResponseEntity<String> addkisan(@RequestBody Kisan kisan) {
				String sttus = "Invalid";
			    if (kisan != null) {
			    	sttus = kisanService.addKisan(kisan);
			    }
			    HttpHeaders httpHeaders = new HttpHeaders();
	
			    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
			}
}
