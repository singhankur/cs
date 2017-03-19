package com.cs.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.Constants.Status;
import com.cs.mongo.model.AdminUser;
import com.cs.rest.services.SessionManagementService;
import com.cs.rest.services.UserServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserServices us;
	@Autowired
	private SessionManagementService sessionManagementService;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody AdminUser adminUser) {
		String sttus = "Invalid";
		HttpHeaders httpHeaders = new HttpHeaders();
	    if (adminUser != null) {
	    	String sessionResponse = sessionManagementService.validateSession(adminUser.getSession_id());
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    		 return new ResponseEntity<String>(Status.sessionInvalid, httpHeaders,HttpStatus.UNAUTHORIZED);
	    	
	    	sttus = us.addAdminUser(adminUser);
	    }
	    
	    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
	public ResponseEntity<List<String>> getAllUser(@RequestBody String session_id) {
		HttpHeaders httpHeaders = new HttpHeaders();
		List<String> allUser=null;
		if (session_id != null) {
			String sessionResponse = sessionManagementService.validateSession(session_id);
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    	    return new ResponseEntity<List<String>>(allUser, httpHeaders,HttpStatus.UNAUTHORIZED);
			allUser = us.getAllUser(session_id);
	    }
	 
	    return new ResponseEntity<List<String>>(allUser, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteUser(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		String status = "Invalid";
		 HttpHeaders httpHeaders = new HttpHeaders();
		String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<String>(status, httpHeaders,HttpStatus.UNAUTHORIZED);
		
    	
		status = us.deleteUser(retMap.get("userName"),retMap.get("session_id"));
	   
	  
	    return new ResponseEntity<String>(status, httpHeaders,HttpStatus.OK);
	}
	
}
