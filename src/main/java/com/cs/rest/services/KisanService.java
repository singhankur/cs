package com.cs.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.cs.mongo.model.Kisan;
import com.cs.mongo.model.Vyapari;
import com.cs.mongo.repository.KisanRepository;


@Service
public class KisanService {
	

	Kisan kisan;
	@Autowired
	KisanRepository kisanRepository;
	@Autowired
	VyapariServices vyapariServices;

	public String addKisan(Kisan nweKisan) {
		
		if(StringUtils.isEmpty(nweKisan.getSlipNumber()) || nweKisan.getSlipNumber().equals("slipNumber"))
			return "Invalid Slip Number";

		if(searchPreviousSlipNumber(nweKisan.getSlipNumber()))
			return "Kisan or Vypari Exist, Try Update";
		
		kisan  = new Kisan();
		kisan.setCreatedDate(new Date());
		kisan.setLastModifiedDate(new Date());
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
