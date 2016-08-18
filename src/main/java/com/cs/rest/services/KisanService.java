package com.cs.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.cs.mongo.model.Kisan;
import com.cs.mongo.repository.KisanRepository;

@Service
public class KisanService {
	

	Kisan kisan;
	@Autowired
	KisanRepository kisanRepository;

	public String addKisan(Kisan nweKisan) {
		
		if(StringUtils.isEmpty(nweKisan.getSlipNumber()) || nweKisan.getSlipNumber().equals("slipNumber"))
			return "Invalid Slip Number";

		kisan  = new Kisan();
		kisan.setCreatedDate(new Date());
		kisan.setLastModifiedDate(new Date());
		kisan.setMaintainedBy(nweKisan.getMaintainedBy());
		kisan.setSlipNumber(nweKisan.getSlipNumber());
		kisan.setName(nweKisan.getName());
		kisan.setFatherName(nweKisan.getFatherName());
		kisan.setLotNumber(nweKisan.getLotNumber());
		kisan.setPickupPrice(nweKisan.getPickupPrice());
		kisan.setDropPrice(nweKisan.getDropPrice());
		kisan.setMobile(nweKisan.getMobile());
		kisan.setNoOfPacket(nweKisan.getNoOfPacket());
		kisan.setAddress(nweKisan.getAddress());
		kisan.setTypeOfPotato(nweKisan.getTypeOfPotato());
		kisan.setProfileType(nweKisan.getProfileType());
		kisan.setTotalWeight(nweKisan.getTotalWeight());
		kisanRepository.save(kisan);
		
		return "Kisan Added Successfully";
	}
	
	public Kisan getKisan(String slipNumber){
		List<Kisan> kisanList =  kisanRepository.findBySlipNumber(slipNumber);
		return kisanList.get(0);
	}
}
