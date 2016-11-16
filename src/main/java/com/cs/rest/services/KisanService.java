package com.cs.rest.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cs.Constants.ApplicationConstants;
import com.cs.mongo.model.ColdStorageProperty;
import com.cs.mongo.model.Kisan;
import com.cs.mongo.repository.ColdStoragePropertyRepository;
import com.cs.mongo.repository.KisanRepository;
import com.cs.utility.DateUtility;


@Service
public class KisanService {
	

	Kisan kisan;
	@Autowired
	KisanRepository kisanRepository;
	@Autowired
	ColdStoragePropertyRepository csProperty;

	public String addKisan(Kisan nweKisan) {
		
		if(StringUtils.isEmpty(nweKisan.getSlipNumber()) || nweKisan.getSlipNumber().equals("slipNumber"))
			return "Invalid Slip Number";

		if(searchPreviousSlipNumber(nweKisan.getSlipNumber()))
			return "Kisan or Vypari Exist, Try Update";
		
		kisan  = new Kisan();
		try {
			kisan.setCreatedDate(DateUtility.getDateWithTimeZone());
			kisan.setLastModifiedDate(DateUtility.getDateWithTimeZone());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		kisan.setMaintainedBy(nweKisan.getMaintainedBy());
		kisan.setSlipNumber(nweKisan.getSlipNumber());
		kisan.setName(nweKisan.getName());
		kisan.setFatherName(nweKisan.getFatherName());
		kisan.setLotNumber(nweKisan.getLotNumber());
		kisan.setMobile(nweKisan.getMobile());
		kisan.setNoOfPacket(nweKisan.getNoOfPacket());
		kisan.setAddress(nweKisan.getAddress());
		kisan.setTypeOfPotato(nweKisan.getTypeOfPotato());
		kisan.setProfileType(nweKisan.getProfileType());
		kisan.setDropPricesettled(nweKisan.isDropPricesettled());
		String yearOrDate;
		Integer previousPacket =0;
		try {
			yearOrDate = DateUtility.getDateFromDate(DateUtility.getDateWithTimeZone());
			ColdStorageProperty csp = csProperty.findByYearorDate(yearOrDate);
			if(csp!=null)
				previousPacket = csp.getPacketIn();
			else{
				csp = new ColdStorageProperty();
				csp.setYearorDate(yearOrDate);
			}
			csp.setPacketIn(previousPacket+ Integer.parseInt(kisan.getNoOfPacket()));
			csp.setActionPerformed(ApplicationConstants.ROOT_ACTION);
			csProperty.save(csp);
			
			ColdStorageProperty newCsp = new ColdStorageProperty();
			newCsp.setActionPerformed(ApplicationConstants.PACKET_IN);
			newCsp.setPacketIn(Integer.parseInt(kisan.getNoOfPacket()));
			newCsp.setYearorDate(DateUtility.getDateWithTimeZone());
			csProperty.save(newCsp);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		kisanRepository.save(kisan);
		
		return "Kisan Added Successfully";
	}
	
	 Boolean searchPreviousSlipNumber(String slipNumber) {
		Kisan k = getKisan(slipNumber);
		if(k!=null)
			return true;
		/*Vyapari v = vyapariServices.getVypari(slipNumber);
		if(v!=null)
			return true;*/
		
		return false;
	}

	public Kisan getKisan(String slipNumber){
		List<Kisan> kisanList =  kisanRepository.findBySlipNumber(slipNumber);
			if(kisanList.size()==0)
				return null;
		return kisanList.get(0);
	}
}
