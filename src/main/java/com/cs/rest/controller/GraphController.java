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
import com.cs.request.models.GraphModel;
import com.cs.rest.services.GraphServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class GraphController {
	
	@Autowired
	GraphServices gs;

	
	@CrossOrigin
	@RequestMapping(value = "/packetGraphPerDay", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<GraphModel>> packetGraphPerDay(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<GraphModel> graphModels;
		graphModels = gs.packetGraphPerDay(retMap.get("date"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<GraphModel>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/revenueGraphPerDay", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<GraphModel>> revenueGraphPerDay(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<GraphModel> graphModels;
		graphModels = gs.revenueGraphPerDay(retMap.get("date"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<GraphModel>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/packetGraphStartEnd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<GraphModel>> packetGraphStartEnd(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<GraphModel> graphModels;
		graphModels = gs.packetGraphStartEnd(retMap.get("startDate"),retMap.get("endDate"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<GraphModel>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/revenueGraphStartEnd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<GraphModel>> revenueGraphStartEnd(@RequestBody String json) {
		Map<String, String> retMap = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<GraphModel> graphModels;
		graphModels = gs.revenueGraphStartEnd(retMap.get("startDate"),retMap.get("endDate"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<GraphModel>>(graphModels, httpHeaders,HttpStatus.OK);
	}
	
}
