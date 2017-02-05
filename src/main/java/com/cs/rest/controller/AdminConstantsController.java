package com.cs.rest.controller;

import java.text.ParseException;
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

import com.cs.mongo.model.AdminConstants;
import com.cs.rest.services.AdminConstantsServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class AdminConstantsController {

	@Autowired
	AdminConstantsServices adminConstantsServices;
	AdminConstants adminConstants;

	@CrossOrigin
	@RequestMapping(value = "/getAdminConstant", method = RequestMethod.POST)
	public ResponseEntity<AdminConstants> getAdminConstant(@RequestBody String year) {
		Map<String, String> retMap = new Gson().fromJson(year, new TypeToken<HashMap<String, Object>>() {}.getType());
		
		
		if (year != null) {
			adminConstants= adminConstantsServices.getAdminConstant(retMap.get("year"));
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<AdminConstants>(adminConstants, httpHeaders, HttpStatus.OK);
	}
	@RequestMapping(value = "/getAllAdminConstant", method = RequestMethod.GET)
	public List<AdminConstants> getAllAdminConstant() {
			return adminConstantsServices.getAllAdminConstant();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/setAdminConstant", method = RequestMethod.POST)
	public ResponseEntity<String> setAdminConstant(@RequestBody AdminConstants adminConstants) {
		String response = "Invalid";
		if (adminConstants != null) {
			response = adminConstantsServices.setAdminConstant(adminConstants);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<String>(response, httpHeaders, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/ExtraAdminConstant", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String,String>> ExtraAdminConstant(@RequestBody String json) throws ParseException {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		Map<String,String> status = new HashMap<>();
		status = adminConstantsServices.ExtraAdminConstant(retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<Map<String,String>>(status, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/updateRacNo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateRacNo(@RequestBody String json) throws ParseException {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		String status ;
		status = adminConstantsServices.updateRacNo(retMap.get("session_id"),retMap.get("slipNumber"),retMap.get("lotNumber"),retMap.get("type"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<String>(status, httpHeaders,HttpStatus.OK);
	}

}
