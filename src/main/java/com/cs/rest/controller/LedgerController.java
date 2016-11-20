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

import com.cs.mongo.model.LedgerAccount;
import com.cs.mongo.model.LedgerTransactions;
import com.cs.request.models.LedgerSearchParams;
import com.cs.request.models.LedgerTransactionsParams;
import com.cs.rest.services.LedgerAccountServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class LedgerController {
	
	@Autowired
	LedgerAccountServices ledgerAccountServices;
	
	@RequestMapping(value = "/createLedger", method = RequestMethod.POST)
	public ResponseEntity<String> createLedger(@RequestBody LedgerAccount ledgerAccount) {
		String sttus = "Invalid";
	    if (ledgerAccount != null) {
	    	sttus = ledgerAccountServices.addLedgerAccount(ledgerAccount);
	    }
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/searchLedgerAccount", method = RequestMethod.POST)
	public ResponseEntity<List<LedgerAccount>> searchLedgerAccount(@RequestBody LedgerSearchParams ledgerSearchParams) {
		List<LedgerAccount> status = ledgerAccountServices.searchLegerAccount(ledgerSearchParams);
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<LedgerAccount>>(status, httpHeaders,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ledgerAccountInformation", method = RequestMethod.POST)
	public ResponseEntity<LedgerAccount> ledgerAccountInformation(@RequestBody String requestInputParam) {
		Map<String, String> retMap = new Gson().fromJson(requestInputParam, new TypeToken<HashMap<String, Object>>() {}.getType());
		LedgerAccount ledgerAccount = ledgerAccountServices.getledgerAccountInformation(retMap.get("ledger_id"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<LedgerAccount>(ledgerAccount, httpHeaders,HttpStatus.OK);
	}
	
	//LedgerTransactionsParams
	@RequestMapping(value = "/ledgerTransactionIformation", method = RequestMethod.POST)
	public ResponseEntity<List<LedgerTransactions>> ledgerTransactionIformation(@RequestBody String requestInputParam) {
		Map<String, String> retMap = new Gson().fromJson(requestInputParam, new TypeToken<HashMap<String, Object>>() {}.getType());
		List<LedgerTransactions> listLedgerTransactions = ledgerAccountServices.getledgerTransactionIformation(retMap.get("ledger_id"),retMap.get("session_id"));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<List<LedgerTransactions>>(listLedgerTransactions, httpHeaders,HttpStatus.OK);
	}

	@RequestMapping(value = "/createLedgerTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> createLedgerTransaction(@RequestBody LedgerTransactionsParams ledgerTransactionsParams) {
		String status = "Invalid";
		status = ledgerAccountServices.createLedgerTransaction(ledgerTransactionsParams);
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<String>(status, httpHeaders,HttpStatus.OK);
	}
	
}
