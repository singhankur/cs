package com.cs.rest.services;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cs.Constants.ApplicationConstants;
import com.cs.mongo.model.Kisan;
import com.cs.mongo.model.Transactions;
import com.cs.mongo.model.Vyapari;
import com.cs.mongo.repository.TransactionsRepository;
import com.cs.request.models.TransactionsParams;
import com.cs.utility.DateUtility;

@Service
public class TransactionsService {
	
	@Autowired
	private CounterService counterService;
	@Autowired
	private KisanService kisanService;
	@Autowired
	private VyapariServices vyapariServices;
	
	@Autowired
	AdminConstantsServices adminConstant;
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	private static final Logger logger = LoggerFactory.getLogger(TransactionsService.class);
	
	public String createTransaction(TransactionsParams transaction){
		logger.info(this.getClass().getName() + "@ createTransaction");
		Transactions trans = new Transactions();
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
				Boolean dropsettled = kk.isDropPricesettled();
				System.out.println("kisan");
				saveTransaction(transaction,dropsettled);
				return "Successful Transaction For Kisan";
			}
			return "Not Enough Packet";
		}
		if(vv!=null){
			if(getReaminingPacket(vv.getNoOfPacket() , transaction.getSlipNumber()) >= Integer.parseInt(transaction.getPacketTaken()))
			{
				System.out.println("vypari");
				Boolean dropsettled = vv.isDropPricesettled();
				saveTransaction(transaction,dropsettled);
				return "Successful Transaction For Vypari";
			}
			return "Not Enough Packet";
		}
		return "Invalid Scenario";
	}
	
	

	private void saveTransaction(TransactionsParams transaction, Boolean dropsettled) {
		logger.info(this.getClass().getName() + "@ saveTransaction");
		Transactions trans = new Transactions();
		trans.setSlipNumber(transaction.getSlipNumber());
		trans.setTransactionID(counterService.getNextSequence(ApplicationConstants.TRANSACTION_COLLECTION));
		trans.setPacketTaken(Integer.parseInt(transaction.getPacketTaken()));
		trans.setAmountPaid(Double.parseDouble(transaction.getAmountPaid()));
		trans.setTotalWeight(transaction.getTotalWeight());
		trans.setTotalWeightAmount(transaction.getTotalWeightAmount());
		trans.setBuyerID(transaction.getFromWhichKisanSlipNumber());
		trans.setMaintainedBy(transaction.getSession_id());
		trans.setBuyer(setBuyer(transaction.getFromWhichKisanSlipNumber(),transaction.getBuyer()));
		trans.setTotalSmallPacket(Integer.parseInt(transaction.getTotalSmallPaket()));
		trans.setHaveSmallPacket(transaction.isSmallPacket());
		trans.setSettled(dropsettled);
		try {
			trans.setCreatedDate(DateUtility.getDateWithTimeZone());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		trans.setIsDeleted(ApplicationConstants.IS_NOT_DELETED);
		
		Double intermediateAmount = calculateIntermediateAmount(transaction.getPacketTaken(),transaction.getSlipNumber(),transaction.getFromWhichKisanSlipNumber(),transaction.getTotalWeight(),transaction.getSeller());
		Double samllPacketAmount = calculateSmallPacketAmmount(transaction.getTotalSmallPaket(),transaction.getSeller());
		trans.setTotalDropPrice(calculateTotalDropPrice(transaction.getPacketTaken(),transaction.getSlipNumber(),transaction.getSeller()));
		trans.setSmallPacketAmount(samllPacketAmount);
		
		
		Double totalAmount = intermediateAmount+samllPacketAmount;
		trans.setTotalAmount(totalAmount);
		
		
		trans.setTransactionComplete(findIsTransactionComplete(transaction.getPacketTaken(),transaction.getAmountPaid(),transaction.getSlipNumber(), totalAmount));
		transactionsRepository.save(trans);
	}






	private boolean findIsTransactionComplete(String packetTaken, String amountPaid, String slipNumber, Double totalAmount) {
		
		Integer remainingPacket =0;
		Double remainingAnount =0D;
		Double amountPaids =0D;
		
		List<Transactions> allTransaction = getAllPreviousTransaction(slipNumber);
		
		for(Transactions t : allTransaction){
			remainingPacket += t.getPacketTaken();
			remainingAnount += t.getTotalAmount();
			amountPaids += t.getAmountPaid();
		}
		
		if((remainingPacket-Integer.parseInt(packetTaken)==0) && ((remainingAnount+totalAmount)-(amountPaids+Double.parseDouble(amountPaid))==0))
		return true;
		
		return false;
	}



	private Double calculateIntermediateAmount(String packetTaken,String slipNumber, String fromWhichKisanSlipNumber, double weightAmount, String seller) {
		
		Double intermediateAmount = 0D;
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR); 
		 
		if(fromWhichKisanSlipNumber.equals(ApplicationConstants.NA))
			return weightAmount;
		else{
			if(seller.equalsIgnoreCase("K")){
				intermediateAmount = Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getStoragePricePerPacketKisan()) * Integer.parseInt(packetTaken);
				if(!kisanService.getKisan(slipNumber).isDropPricesettled())
					intermediateAmount += Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getDropPricePerPacketKisan()) * Integer.parseInt(packetTaken) ;
			}
				
			else{
				intermediateAmount = Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getStoragePricePerPacketVypari()) * Integer.parseInt(packetTaken);
				if(!vyapariServices.getVypari(slipNumber).isDropPricesettled())
					intermediateAmount += Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getDropPricePerPacketVypari()) * Integer.parseInt(packetTaken) ;
			}	

		}
			return intermediateAmount;
		
	}



	private Double calculateSmallPacketAmmount(String smallPacket,String seller) {
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR); 
		Double intermediateAmount = 0D;
		if(seller.equalsIgnoreCase("K")){
			intermediateAmount = Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getStoragePricePerSmallPacketKisan()) * Integer.parseInt(smallPacket);
			}
			
		else{
			intermediateAmount = Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getStoragePricePerSmallPacketVypari()) * Integer.parseInt(smallPacket);
			}	
		
		return intermediateAmount;
	}



	private Double calculateTotalDropPrice(String packetTaken,String slipNumber,String seller) {
		Double intermediateAmount = 0D;
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR); 
		
		if(seller.equalsIgnoreCase("K")){
			
			if(!kisanService.getKisan(slipNumber).isDropPricesettled())
				intermediateAmount += Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getDropPricePerPacketKisan()) * Integer.parseInt(packetTaken) ;
		}
			
		else{
			
			if(!vyapariServices.getVypari(slipNumber).isDropPricesettled())
				intermediateAmount += Double.parseDouble(adminConstant.getAdminConstant(String.valueOf(year)).getDropPricePerPacketVypari()) * Integer.parseInt(packetTaken) ;
		}
		
		return intermediateAmount;
	}



	private String setBuyer(String fromWhichKisanSlipNumber, String buyer) {
		if(fromWhichKisanSlipNumber.equals(ApplicationConstants.NA))
			return buyer;
		else
			return "K:"+fromWhichKisanSlipNumber;
			
	}



	private Integer getReaminingPacket(String noOfPacket, String slipNumber) {
		logger.info(this.getClass().getName() + "@ getReaminingPacket");
		List<Transactions> allTransaction = getAllPreviousTransaction(slipNumber);
		int remainingPacket = 0;
		int packetTaken = 0;
		for(Transactions t : allTransaction){
			if(t.getIsDeleted()==null || !t.getIsDeleted())
				packetTaken += t.getPacketTaken();
		}
		remainingPacket = Integer.parseInt(noOfPacket) - packetTaken;
		return remainingPacket;
	}



	public List<Transactions> getAllPreviousTransaction(String slipNumber){
		logger.info(this.getClass().getName() + "@ getAllPreviousTransaction");
		 List<Transactions> alltransactions =  transactionsRepository.findBySlipNumber(slipNumber);
		 System.out.println(alltransactions);
		 return alltransactions;
	}

	public List<Transactions> getUserTransactions(String slipNumber) {
		logger.info(this.getClass().getName() + "@ getUserTransactions");
		System.out.println(slipNumber);
		return getAllPreviousTransaction(slipNumber);
	}



	public String deleteTransaction(TransactionsParams transaction) {
		
		return null;
	}

}
