package com.cs.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.Constants.Status;
import com.cs.mongo.model.User;
import com.cs.request.models.SearchParams;
import com.cs.rest.services.SearchServices;
import com.cs.rest.services.SessionManagementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class SearchController {

	@Autowired
	SearchServices searchServices;
	@Autowired
	private SessionManagementService sessionManagementService;
	
	List<SearchParams> searchResult ;
	List<User> profiles ;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<SearchParams>> search(@RequestBody SearchParams searchParams) {
	    HttpHeaders httpHeaders = new HttpHeaders();
	    if (searchParams != null) {
	    	String sessionResponse = sessionManagementService.validateSession(searchParams.getSession_id());
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    	    return new ResponseEntity<List<SearchParams>>(searchResult, httpHeaders,HttpStatus.UNAUTHORIZED);
			
	    	searchResult = searchServices.search(searchParams);
	    }
	
	    return new ResponseEntity<List<SearchParams>>(searchResult, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<User>> getTest(@RequestBody String json) {
	
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		 HttpHeaders httpHeaders = new HttpHeaders();
		String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<List<User>>(profiles, httpHeaders,HttpStatus.UNAUTHORIZED);
		
    	
		profiles = searchServices.searchProfiles(retMap.get("slipNumber"),retMap.get("session_id"));
	   
	 
	    return new ResponseEntity<List<User>>(profiles, httpHeaders,HttpStatus.OK);
	}
	
	@RequestMapping("/test")
	public List<User> getany (
			@RequestParam(value = "s", defaultValue = "s") String s) 
					throws MessagingException {
		return searchServices.searchProfiles(s);
	}
	
}
