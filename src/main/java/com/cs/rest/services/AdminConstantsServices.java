package com.cs.rest.services;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		/*Map<String, AdminConstants> yearWiseApplicatinconstat = new HashMap<>();
		
		for (AdminConstants adminConstants : allApplicationConstant) {
			yearWiseApplicatinconstat.put(adminConstants.getYear(), adminConstants);
		}*/
		return allApplicationConstant;
	}

	public Map<String, String> ExtraAdminConstant(String string) throws ParseException {
		
		String todayDate  = DateUtility.getDateWithTimeZone();
		
		List<Transactions> allTransactions = transactionsService.getallTransaction(todayDate);
		Map<String, String> result = new HashMap<>();
		
		
		Integer packetIn = 0;
		Double todayRevenue = 0D;
		Integer packetOut = 0;
		for(Transactions t :allTransactions ){
			packetIn += t.getPacketTaken();
			todayRevenue += t.getAmountPaid();
			packetOut += t.getPacketTaken();
			
		}
		String yearOrDate;
		ColdStorageProperty csp = null;
		try {
			yearOrDate = DateUtility.getDateFromDate(DateUtility.getDateWithTimeZone());
			 csp = csProperty.findByYearorDate(yearOrDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		result.put("PacketIn", packetIn.toString());
		result.put("TodayRevenue", todayRevenue.toString());
		result.put("PacketOut", packetOut.toString());
		result.put("RemainingPacketinClodStorage", csp.getRemainingPacket().toString());
		result.put("PacketInColdStorage", csp.getPacketIn().toString());
		
		return result;
	}

}
