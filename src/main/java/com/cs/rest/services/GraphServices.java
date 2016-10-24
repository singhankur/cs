package com.cs.rest.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.mongo.model.Transactions;
import com.cs.request.models.GraphModel;
import com.cs.utility.DateUtility;

@Service
public class GraphServices {
	
	@Autowired
	TransactionsService ts;
	
	public List<GraphModel> revenueGraphPerDay(String date, String session_id) {
		
		List<Transactions> allTransaction = ts.getallTransaction(date);
		List<GraphModel> revenueModel = new ArrayList<>();
		DecimalFormat twodigits = new DecimalFormat("00");
		
	
		Map<String,Double> revenueMap = new HashMap<>();
		for(Transactions t : allTransaction){
			String createdDate = t.getCreatedDate();
			String timeFromDate = DateUtility.getHoursFromDate(createdDate);
			Double oldValue = revenueMap.get(timeFromDate);
			if(oldValue ==null)
				oldValue = 0D;
			
			oldValue += t.getAmountPaid();
			revenueMap.put(timeFromDate, oldValue);	
		}

		for(int i=0;i<24;i++){
			GraphModel gm = new GraphModel();
			gm.setX_axis(twodigits.format(i) + ":00-Hrs");
			gm.setY_axis(Double.toString(revenueMap.get(twodigits.format(i))));
			revenueModel.add(gm);
		}
		
		return revenueModel;
	}

	public List<GraphModel> packetGraphPerDay(String date, String session_id) {
		
		List<Transactions> allTransaction = ts.getallTransaction(date);
		List<GraphModel> packetModel = new ArrayList<>();
		DecimalFormat twodigits = new DecimalFormat("00");
		
	
		Map<String,Double> revenueMap = new HashMap<>();
		for(Transactions t : allTransaction){
			String createdDate = t.getCreatedDate();
			String timeFromDate = DateUtility.getHoursFromDate(createdDate);
			Double oldValue = revenueMap.get(timeFromDate);
			if(oldValue ==null)
				oldValue = 0D;
			
			oldValue += t.getPacketTaken();
			revenueMap.put(timeFromDate, oldValue);	
		}

		for(int i=0;i<24;i++){
			GraphModel gm = new GraphModel();
			gm.setX_axis(twodigits.format(i) + ":00-Hrs");
			gm.setY_axis(Double.toString(revenueMap.get(twodigits.format(i))));
			packetModel.add(gm);
		}
		
		return packetModel;
		

	}

	
	public List<GraphModel> packetGraphLastSevenDays(String startDate, String session_id) {
		List<Transactions> allTransaction = ts.getallTransactionPrevious7Days(startDate);
		List<GraphModel> packetModel = new ArrayList<>();
		DecimalFormat twodigits = new DecimalFormat("00");
		
	
		Map<String,Double> revenueMap = new HashMap<>();
		for(Transactions t : allTransaction){
			String createdDate = t.getCreatedDate();
			String timeFromDate = DateUtility.getDateFromDate(createdDate);
			Double oldValue = revenueMap.get(timeFromDate);
			if(oldValue ==null)
				oldValue = 0D;
			
			oldValue += t.getPacketTaken();
			revenueMap.put(timeFromDate, oldValue);	
		}

		for(int i=0;i<24;i++){
			GraphModel gm = new GraphModel();
			gm.setX_axis(twodigits.format(i) + ":00-Hrs");
			gm.setY_axis(Double.toString(revenueMap.get(twodigits.format(i))));
			packetModel.add(gm);
		}
		
		return packetModel;
	}

	public List<GraphModel> revenueGraphLastSevenDays(String startDate, String session_id) {
		
		List<Transactions> allTransaction = ts.getallTransactionPrevious7Days(startDate);
		List<GraphModel> revenueModel = new ArrayList<>();
		DecimalFormat twodigits = new DecimalFormat("00");
		
	
		Map<String,Double> revenueMap = new HashMap<>();
		for(Transactions t : allTransaction){
			String createdDate = t.getCreatedDate();
			String timeFromDate = DateUtility.getDateFromDate(createdDate);
			Double oldValue = revenueMap.get(timeFromDate);
			if(oldValue ==null)
				oldValue = 0D;
			
			oldValue += t.getAmountPaid();
			revenueMap.put(timeFromDate, oldValue);	
		}

		for(int i=0;i<24;i++){
			GraphModel gm = new GraphModel();
			gm.setX_axis(twodigits.format(i) + ":00-Hrs");
			gm.setY_axis(Double.toString(revenueMap.get(twodigits.format(i))));
			revenueModel.add(gm);
		}
		
		return revenueModel;
	}

}
