package com.cs.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cs.Constants.ApplicationConstants;
import com.cs.mongo.model.Kisan;
import com.cs.mongo.model.Transactions;
import com.cs.mongo.model.Vyapari;
import com.cs.mongo.repository.TransactionsRepository;
import com.cs.request.models.TransactionsParams;

@Service
public class TransactionsService {
	
	@Autowired
	private CounterService counterService;
	@Autowired
	private KisanService kisanService;
	@Autowired
	private VyapariServices vyapariServices;
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	
	public String createTransaction(TransactionsParams transaction){
		Transactions trans = new Transactions();
		System.out.println(transaction.toString() + "here");
		trans.setTransactionID(counterService.getNextSequence("transactions"));
		
		String slipNumber = transaction.getSlipNumber();
		if(StringUtils.isEmpty(slipNumber))
			return "Invalid Slip Number";
			
		
		Kisan kk = kisanService.getKisan(slipNumber);
		Vyapari vv = vyapariServices.getVypari(slipNumber);
		
		if(kk == null && vv == null)
			return "No Kisan or Vypari Found";	
				
		if(kk!=null){
			if(getReaminingPacket(kk.getNoOfPacket() , transaction.getSlipNumber()) >= Integer.parseInt(transaction.getPacketTaken()))
			{
				System.out.println("kisan");
				saveTransaction(transaction);
				return "Successful Transaction For Kisan";
			}
			return "Not Enough Packet";
		}
		if(vv!=null){
			if(getReaminingPacket(vv.getNoOfPacket() , transaction.getSlipNumber()) >= Integer.parseInt(transaction.getPacketTaken()))
			{
				System.out.println("vypari");
				saveTransaction(transaction);
				return "Successful Transaction For Vypari";
			}
			return "Not Enough Packet";
		}
		return "Invalid Scenario";
	}
	
	

	private void saveTransaction(TransactionsParams transaction) {
		Transactions trans = new Transactions();
		trans.setSlipNumber(transaction.getSlipNumber());
		trans.setTransactionID(counterService.getNextSequence(ApplicationConstants.TRANSACTION_COLLECTION));
		trans.setAmountPaid(Double.parseDouble(transaction.getAmountPaid()));
		trans.setTotalAmount(Double.parseDouble(transaction.getTotalAmount()));
		trans.setPacketTaken(Integer.parseInt(transaction.getPacketTaken()));
		trans.setBuyer(transaction.getBuyer());
		trans.setCreatedDate(new Date());
		transactionsRepository.save(trans);
	}



	private Integer getReaminingPacket(String noOfPacket, String slipNumber) {
		List<Transactions> allTransaction = getAllPreviousTransaction(slipNumber);
		int remainingPacket = 0;
		int packetTaken = 0;
		for(Transactions t : allTransaction){
			packetTaken += t.getPacketTaken();
		}
		remainingPacket = Integer.parseInt(noOfPacket) - packetTaken;
		return remainingPacket;
	}



	public List<Transactions> getAllPreviousTransaction(String slipNumber){
		 List<Transactions> alltransactions =  transactionsRepository.findBySlipNumber(slipNumber);
		 System.out.println(alltransactions);
		 return alltransactions;
	}



	public List<Transactions> getUserTransactions(String slipNumber) {
		System.out.println(slipNumber);
		return getAllPreviousTransaction(slipNumber);
	}

}
