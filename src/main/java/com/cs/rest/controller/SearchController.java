package com.cs.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.mongo.model.SearchParams;
import com.cs.mongo.model.User;
import com.cs.rest.services.SearchServices;

@RestController
public class SearchController {

	@Autowired
	SearchServices searchServices;
	
	List<SearchParams> searchResult ;
	List<User> profiles ;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<SearchParams>> search(@RequestBody SearchParams searchParams) {
		
	    if (searchParams != null) {
	    	searchResult = searchServices.search(searchParams);
	    }
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<SearchParams>>(searchResult, httpHeaders,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<User>> getTest(@RequestBody Map<String, String> json) {
	
		profiles = searchServices.searchProfiles(json.get("slipNumber"),json.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<User>>(profiles, httpHeaders,HttpStatus.OK);
	}
	
	@RequestMapping("/login")
	public String register (
			@RequestParam(value = "username", defaultValue = "username") String username,
			@RequestParam(value = "password", defaultValue = "password") String password	) 
					throws MessagingException {
		return "Wait This needs to be implemented";
	}
	
	@RequestMapping("/test")
	public List<User> getany (
			@RequestParam(value = "s", defaultValue = "s") String s) 
					throws MessagingException {
		
		return searchServices.searchProfiles(s);
	}
	
}
