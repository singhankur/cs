package com.cs.rest.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.cs.mongo.model.Vyapari;
import com.cs.mongo.repository.VypariRepository;

@Service
public class VyapariServices {

	@Autowired
	VypariRepository vypariRepository;
	
	public String addvypari(Vyapari newVypari) {
		if(StringUtils.isEmpty(newVypari.getSlipNumber()) || newVypari.getSlipNumber().equals("slipNumber"))
			return "Invalid Slip Number";

		Vyapari vypari  = new Vyapari();
		vypari.setCreatedDate(new Date());
		vypari.setLastModifiedDate(new Date());
		vypari.setMaintainedBy(newVypari.getMaintainedBy());
		vypari.setSlipNumber(newVypari.getSlipNumber());
		vypari.setName((newVypari.getName()));
		vypari.setFatherName(newVypari.getFatherName());
		vypari.setLotNumber(newVypari.getLotNumber());
		vypari.setPickupPrice(newVypari.getPickupPrice());
		vypari.setDropPrice(newVypari.getDropPrice());
		vypari.setMobile(newVypari.getMobile());
		vypari.setNoOfPacket(newVypari.getNoOfPacket());
		vypari.setAddress(newVypari.getAddress());
		vypari.setTypeOfPotato(newVypari.getTypeOfPotato());
		vypari.setProfileType(newVypari.getProfileType());
		vypari.setTotalWeight(newVypari.getTotalWeight());
		vypariRepository.save(vypari);
		
		return "Vypari Added Successfully";
	}

}
