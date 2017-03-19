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

import com.cs.Constants.Status;
import com.cs.mongo.model.LedgerAccount;
import com.cs.mongo.model.LedgerTransactions;
import com.cs.request.models.LedgerSearchParams;
import com.cs.request.models.LedgerTransactionsParams;
import com.cs.rest.services.LedgerAccountServices;
import com.cs.rest.services.SessionManagementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
public class LedgerController {
	
	@Autowired
	LedgerAccountServices ledgerAccountServices;
	@Autowired
	private SessionManagementService sessionManagementService;
	
	@RequestMapping(value = "/createLedger", method = RequestMethod.POST)
	public ResponseEntity<String> createLedger(@RequestBody LedgerAccount ledgerAccount) {
		String sttus = "Invalid";
	    HttpHeaders httpHeaders = new HttpHeaders();
	    if (ledgerAccount != null) {
	    	String sessionResponse = sessionManagementService.validateSession(ledgerAccount.getSession_id());
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    	    return new ResponseEntity<String>(Status.sessionInvalid, httpHeaders,HttpStatus.UNAUTHORIZED);
			
	    	sttus = ledgerAccountServices.addLedgerAccount(ledgerAccount);
	    }
	    return new ResponseEntity<String>(sttus, httpHeaders,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/searchLedgerAccount", method = RequestMethod.POST)
	public ResponseEntity<List<LedgerAccount>> searchLedgerAccount(@RequestBody LedgerSearchParams ledgerSearchParams) {
	    HttpHeaders httpHeaders = new HttpHeaders();
		String sessionResponse = sessionManagementService.validateSession(ledgerSearchParams.getSession_id());
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<List<LedgerAccount>>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
		
		List<LedgerAccount> status = ledgerAccountServices.searchLegerAccount(ledgerSearchParams);

	    return new ResponseEntity<List<LedgerAccount>>(status, httpHeaders,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ledgerAccountInformation", method = RequestMethod.POST)
	public ResponseEntity<LedgerAccount> ledgerAccountInformation(@RequestBody String requestInputParam) {
		Map<String, String> retMap = new Gson().fromJson(requestInputParam, new TypeToken<HashMap<String, Object>>() {}.getType());
		 HttpHeaders httpHeaders = new HttpHeaders();
		String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<LedgerAccount>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
		
    	
		LedgerAccount ledgerAccount = ledgerAccountServices.getledgerAccountInformation(retMap.get("ledger_id"),retMap.get("session_id"));
	   
	    return new ResponseEntity<LedgerAccount>(ledgerAccount, httpHeaders,HttpStatus.OK);
	}
	
	//LedgerTransactionsParams
	@RequestMapping(value = "/ledgerTransactionIformation", method = RequestMethod.POST)
	public ResponseEntity<List<LedgerTransactions>> ledgerTransactionIformation(@RequestBody String requestInputParam) {
		Map<String, String> retMap = new Gson().fromJson(requestInputParam, new TypeToken<HashMap<String, Object>>() {}.getType());
		HttpHeaders httpHeaders = new HttpHeaders();
		String sessionResponse = sessionManagementService.validateSession(retMap.get("session_id"));
    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
    	    return new ResponseEntity<List<LedgerTransactions>>(null, httpHeaders,HttpStatus.UNAUTHORIZED);
		
    	
		List<LedgerTransactions> listLedgerTransactions = ledgerAccountServices.getledgerTransactionIformation(retMap.get("ledger_id"),retMap.get("session_id"));
	    return new ResponseEntity<List<LedgerTransactions>>(listLedgerTransactions, httpHeaders,HttpStatus.OK);
	}

	@RequestMapping(value = "/createLedgerTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> createLedgerTransaction(@RequestBody LedgerTransactionsParams ledgerTransactionsParams) {
		String status = "Invalid";
		  HttpHeaders httpHeaders = new HttpHeaders();
		  String sessionResponse = sessionManagementService.validateSession(ledgerTransactionsParams.getSession_id());
	    	if(sessionResponse.equalsIgnoreCase(Status.sessionInvalid))
	    	    return new ResponseEntity<String>(Status.sessionInvalid, httpHeaders,HttpStatus.UNAUTHORIZED);
			
		  
		status = ledgerAccountServices.createLedgerTransaction(ledgerTransactionsParams);
		
	    return new ResponseEntity<String>(status, httpHeaders,HttpStatus.OK);
	}
	
}
