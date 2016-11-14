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
	
	public List<Map<String,String>>  revenueGraphPerDay(String date, String session_id) {
		
		List<Transactions> allTransaction = ts.getallTransaction(date);
		List<GraphModel> revenueModel = new ArrayList<>();
		DecimalFormat twodigits = new DecimalFormat("00");
		
	

		Map<String,Double> revenueMap = new HashMap<>();
		for(int i=0;i<24;i++){
			revenueMap.put((twodigits.format(i)) , 0D);
		}
		
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
		
		return getFraphModelToString(revenueModel);
	}
	
	

	public List<Map<String,String>>  packetGraphPerDay(String date, String session_id) {
		
		List<Transactions> allTransaction = ts.getallTransaction(date);
		List<GraphModel> packetModel = new ArrayList<>();
		DecimalFormat twodigits = new DecimalFormat("00");
		
	
		Map<String,Double> revenueMap = new HashMap<>();
		for(int i=0;i<24;i++){
			revenueMap.put((twodigits.format(i)) , 0D);
		}
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
		
		return getFraphModelToString(packetModel);
		

	}

	
	public List<Map<String,String>>  packetGraphLastSevenDays(String startDate, String session_id) {
		List<Transactions> allTransaction = ts.getallTransactionPrevious7Days(startDate);
		
		List<GraphModel> packetModel = new ArrayList<>();
	
		
		Map<String,Double> revenueMap = new HashMap<>();
		
		List<String> last7Days = DateUtility.getPrevious7DaysDate(startDate);
		for(String day : last7Days){
			revenueMap.put(day, 0D);
		}
		
		for(Transactions t : allTransaction){
			String createdDate = t.getCreatedDate();
			String timeFromDate = DateUtility.getDateFromDate(createdDate);
			Double oldValue = revenueMap.get(timeFromDate);
			oldValue += t.getPacketTaken();
			revenueMap.put(timeFromDate, oldValue);	
		}
		

		for(String day : last7Days){
			GraphModel gm = new GraphModel();
			gm.setX_axis(day);
			gm.setY_axis(Double.toString(revenueMap.get(day)));
			packetModel.add(gm);
		}
		
		System.out.println(packetModel + "Packet MOdel ");
		return getFraphModelToString(packetModel);
	}

	public List<Map<String,String>>  revenueGraphLastSevenDays(String startDate, String session_id) {
		List<Transactions> allTransaction = ts.getallTransactionPrevious7Days(startDate);
		List<GraphModel> packetModel = new ArrayList<>();
	
		
		Map<String,Double> revenueMap = new HashMap<>();
		List<String> last7Days = DateUtility.getPrevious7DaysDate(startDate);
		for(String day : last7Days){
			revenueMap.put(day, 0D);
		}
		
		for(Transactions t : allTransaction){
			String createdDate = t.getCreatedDate();
			String timeFromDate = DateUtility.getDateFromDate(createdDate);
			Double oldValue = revenueMap.get(timeFromDate);
			if(oldValue ==null)
				oldValue = 0D;
			
			oldValue += t.getAmountPaid();
			revenueMap.put(timeFromDate, oldValue);	
		}
		

		for(String day : last7Days){
			GraphModel gm = new GraphModel();
			gm.setX_axis(day);
			gm.setY_axis(Double.toString(revenueMap.get(day)));
			packetModel.add(gm);
		}
		
		return getFraphModelToString(packetModel);
	
	
	}
	
	public List<Map<String,String>> getFraphModelToString(List<GraphModel> models){
		List<Map<String,String>>  finalGraph = new ArrayList<>();
		for(GraphModel model : models){
			Map<String,String> graphParam = new HashMap<>();
			graphParam.put(model.getX_axis(), model.getY_axis());	
			finalGraph.add(graphParam);
		}
		
		return finalGraph;
	}

}
