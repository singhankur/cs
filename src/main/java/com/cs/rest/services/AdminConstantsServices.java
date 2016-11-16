package com.cs.rest.services;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.Constants.ApplicationConstants;
import com.cs.mongo.model.AdminConstants;
import com.cs.mongo.model.ColdStorageProperty;
import com.cs.mongo.model.Transactions;
import com.cs.mongo.repository.AdminConstantsRepository;
import com.cs.mongo.repository.ColdStoragePropertyRepository;
import com.cs.utility.DateUtility;

@Service
public class AdminConstantsServices {
	
	@Autowired
	AdminConstantsRepository adminConstantsRepository;
	@Autowired
	TransactionsService transactionsService;
	@Autowired
	ColdStoragePropertyRepository csProperty;

	public AdminConstants getAdminConstant(String year) {
		return adminConstantsRepository.findByYear(year);
	}

	public String setAdminConstant(AdminConstants adminConstants) {
		adminConstantsRepository.save(adminConstants);
		return "Admin Constant Successfully Saved";
	}

	public List<AdminConstants> getAllAdminConstant() {
		
		List<AdminConstants> allApplicationConstant = adminConstantsRepository.findAll();
		return allApplicationConstant;
	}

	public Map<String, String> ExtraAdminConstant(String string) throws ParseException {
		
		String todayDate  = DateUtility.getDateFromDate(DateUtility.getDateWithTimeZone());
		
		List<Transactions> allTransactions = transactionsService.getallTransaction(todayDate);
		Map<String, String> result = new HashMap<>();
		
		
		Integer packetInToday = 0;
		Double todayRevenue = 0D;
		Integer packetOut = 0;
		for(Transactions t :allTransactions ){
			todayRevenue += t.getAmountPaid();
			packetOut += t.getPacketTaken();
			
		}
		String yearOrDate = DateUtility.getDateFromDate(DateUtility.getDateWithTimeZone());;
		ColdStorageProperty csp = null;
		csp = csProperty.findByYearorDate(yearOrDate);
		
		List<ColdStorageProperty> listCsp = csProperty.findByYearorDateStartsWith(yearOrDate);
		
		for(ColdStorageProperty cs : listCsp){
			if(cs.getActionPerformed().equalsIgnoreCase(ApplicationConstants.PACKET_IN))
				packetInToday += cs.getPacketIn();
		}
		
		result.put("PacketInToday", packetInToday.toString());
		result.put("TodayRevenue", todayRevenue.toString());
		result.put("PacketOut", packetOut.toString());
		
		Integer packetInColdStorage=csp.getPacketIn();
		if(packetInColdStorage==null)
			packetInColdStorage =0;
		
		result.put("PacketInColdStorage",packetInColdStorage.toString());
			
		
		if(csp.getRemainingPacket()!=null)
			result.put("RemainingPacketinClodStorage", csp.getRemainingPacket().toString());
		else
			result.put("RemainingPacketinClodStorage", packetInColdStorage.toString());
		
		return result;
	}

}
