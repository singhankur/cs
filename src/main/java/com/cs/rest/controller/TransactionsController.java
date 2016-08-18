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
import org.springframework.web.bind.annotation.RestController;

import com.cs.mongo.model.Transactions;
import com.cs.request.models.TransactionsParams;
import com.cs.rest.services.TransactionsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class TransactionsController {

	@Autowired
	TransactionsService transactionsService;

	@CrossOrigin
	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody TransactionsParams transaction) {
		String sttus = "Invalid";
		if (transaction != null) {
			sttus= transactionsService.createTransaction(transaction);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<String>(sttus, httpHeaders, HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(value = "/getUserTransactions", method = RequestMethod.POST)
	public ResponseEntity<List<Transactions>> getUserTransactions(@RequestBody String slipNumber) {
		Map<String, String> retMap = new Gson().fromJson(slipNumber, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<Transactions> transactions=null;
		if (slipNumber != null) {
			transactions= transactionsService.getUserTransactions(retMap.get("slipNumber"));
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<List<Transactions>>(transactions, httpHeaders, HttpStatus.OK);
	}
}
