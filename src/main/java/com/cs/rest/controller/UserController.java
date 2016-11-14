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

import com.cs.mongo.model.AdminUser;

import com.cs.rest.services.UserServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserServices us;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody AdminUser adminUser) {
		String sttus = "Invalid";
	    if (adminUser != null) {
	    	sttus = us.addAdminUser(adminUser);
	    }
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
	public ResponseEntity<List<String>> getAllUser(@RequestBody String session_id) {
		List<String> allUser=null;
		if (session_id != null) {
			allUser = us.getAllUser(session_id);
	    }
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<String>>(allUser, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteUser(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		String status = "Invalid";
		status = us.deleteUser(retMap.get("userName"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<String>(status, httpHeaders,HttpStatus.OK);
	}
	
}
