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
import com.cs.rest.services.GraphServices;
import com.cs.rest.services.SessionManagementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class GraphController {
	
	@Autowired
	GraphServices gs;
	@Autowired
	private SessionManagementService sessionManagementService;

	
	@CrossOrigin
	@RequestMapping(value = "/packetGraphPerDay", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Map<String,String>>> packetGraphPerDay(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<Map<String,String>> graphModels;
	    HttpHeaders httpHeaders = new HttpHeaders();

		String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<List<Map<String,String>>>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
		
		graphModels = gs.packetGraphPerDay(retMap.get("startDate"),retMap.get("session_id"));
	    return new ResponseEntity<List<Map<String,String>>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/revenueGraphPerDay", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Map<String,String>>> revenueGraphPerDay(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<Map<String,String>> graphModels;
	    HttpHeaders httpHeaders = new HttpHeaders();

		String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<List<Map<String,String>>>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
		
    	
		graphModels = gs.revenueGraphPerDay(retMap.get("startDate"),retMap.get("session_id"));
	    return new ResponseEntity<List<Map<String,String>>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/packetGraphLastSevenDays", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Map<String,String>>> packetGraphLastSevenDays(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<Map<String,String>> graphModels;
		 HttpHeaders httpHeaders = new HttpHeaders();
		 String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    	    return new ResponseEntity<List<Map<String,String>>>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
			
		graphModels = gs.packetGraphLastSevenDays(retMap.get("startDate"),retMap.get("session_id"));
	   
	    return new ResponseEntity<List<Map<String,String>>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/revenueGraphLastSevenDays", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Map<String,String>>> revenueGraphLastSevenDays(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<Map<String,String>> graphModels;
		 HttpHeaders httpHeaders = new HttpHeaders();
		 
		 String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    	    return new ResponseEntity<List<Map<String,String>>>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
			
	    	
		graphModels = gs.revenueGraphLastSevenDays(retMap.get("startDate"),retMap.get("session_id"));
	   
	    return new ResponseEntity<List<Map<String,String>>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
}
