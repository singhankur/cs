package com.cs.rest.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.Constants.ApplicationConstants;
import com.cs.mongo.model.LedgerAccount;
import com.cs.mongo.model.LedgerTransactions;
import com.cs.mongo.repository.LedgerAccountRepository;
import com.cs.mongo.repository.LedgerTransactionsRepository;
import com.cs.request.models.LedgerSearchParams;
import com.cs.request.models.LedgerTransactionsParams;
import com.cs.utility.DateUtility;

@Service
public class LedgerAccountServices {
	
	@Autowired
	private CounterService counterService;
	@Autowired
	private LedgerAccountRepository ledgerAccountRepository;
	@Autowired
	LedgerTransactionsRepository ledgerTransactionsRepository;

	public String addLedgerAccount(LedgerAccount ledgerAccount) {
		
		
		Integer newLedgerId = counterService.getNextSequence(ApplicationConstants.LEDGER_ACCOUNT_COLLECTION);
		ledgerAccount.setLedgerid(String.valueOf(newLedgerId));
		try {
			ledgerAccount.setCreatedDate(DateUtility.getDateWithTimeZone().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ledgerAccountRepository.save(ledgerAccount);
		
		return "Ledger Account Created Successfully";
	}

	public List<LedgerAccount> searchLegerAccount(LedgerSearchParams ledgerSearchParams) {
		
		List<LedgerAccount> list1 = new ArrayList<>();
		List<LedgerAccount> list2 = new ArrayList<>();
		List<LedgerAccount> list3 = new ArrayList<>();
		List<LedgerAccount> list4 = new ArrayList<>();
		List<LedgerAccount> list5 = new ArrayList<>();
		
		
		if(!StringUtils.isEmpty(ledgerSearchParams.getFathersName()))
			list1 =  ledgerAccountRepository.findByFathersNameLikeIgnoreCase(ledgerSearchParams.getFathersName());
		
		if(!StringUtils.isEmpty(ledgerSearchParams.getName()))
			list2 =  ledgerAccountRepository.findByNameLikeIgnoreCase(ledgerSearchParams.getName());
		 

		if(!StringUtils.isEmpty(ledgerSearchParams.getAddress()))
			list3 =  ledgerAccountRepository.findByAddressLikeIgnoreCase(ledgerSearchParams.getAddress());
		
		if(!StringUtils.isEmpty(ledgerSearchParams.getLedger_id()))
			list4 =  ledgerAccountRepository.findByLedgeridLike(ledgerSearchParams.getLedger_id());
		
		if(!StringUtils.isEmpty(ledgerSearchParams.getMobile()))
			list5 =  ledgerAccountRepository.findByMobileLike(ledgerSearchParams.getMobile());
		

		
		List<LedgerAccount> finalLedgerList = new ArrayList<>();
		finalLedgerList.addAll(list1);
		finalLedgerList.addAll(list2);
		finalLedgerList.addAll(list3);
		finalLedgerList.addAll(list4);
		finalLedgerList.addAll(list5);
		
		Map<String , LedgerAccount> ledgerAccountFinalList = new HashMap<>();
		for(LedgerAccount ledgerAccount : finalLedgerList){
			ledgerAccountFinalList.put(ledgerAccount.getLedgerid(), ledgerAccount);
		}
		
		
		return new ArrayList<LedgerAccount>(ledgerAccountFinalList.values());
	}

	public LedgerAccount getledgerAccountInformation(String ledger_id,String session_id) {
		return ledgerAccountRepository.findByLedgerid(ledger_id);
	}

	public List<LedgerTransactions> getledgerTransactionIformation(String ledger_id, String session_id) {
		return ledgerTransactionsRepository.findByLedgerid(ledger_id);
	}

	public String createLedgerTransaction(LedgerTransactionsParams ledgerTransactionsParams) {
		
		
		String transType = ledgerTransactionsParams.getTransactionType();
		LedgerTransactions ledgerTrans = new LedgerTransactions();
		
		String ledgerId = ledgerTransactionsParams.getLedger_id();
		if(ledgerId==null)
			return "Invaid LedgerId";
				
		LedgerAccount ledgeraccount = ledgerAccountRepository.findByLedgerid(ledgerId);
		if(ledgeraccount == null)
			return "Are you Kidding No Leddger Account with this id";
		
		if( transType.equalsIgnoreCase("credit") || (transType.equalsIgnoreCase("debit")) ){
			
			ledgerTrans.setTransactionType("CREDIT");
			if(transType.equalsIgnoreCase("debit"))
				ledgerTrans.setTransactionType("DEBIT");
			
			ledgerTrans.setAmount(ledgerTransactionsParams.getAmount());
			ledgerTrans.setLedgerid(ledgerTransactionsParams.getLedger_id());
			ledgerTrans.setReason(ledgerTransactionsParams.getReason());
			ledgerTrans.setTransactionId(String.valueOf(counterService.getNextSequence(ApplicationConstants.LEDGER_ACCOUNT_COLLECTION)));
			try {
				ledgerTrans.setCreatedDate((DateUtility.getDateWithTimeZone().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ledgerTransactionsRepository.save(ledgerTrans);
		
		return "Transaction is Successful";
	}return "Transaction Not Successful";

	
	}
}
