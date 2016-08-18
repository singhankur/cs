package com.cs.rest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public List<String> getAllVypari() {
		List<Vyapari> vypari = vypariRepository.findAll();
		List<String> vypariNameList = new ArrayList<>();
		for (Vyapari v : vypari) {
			vypariNameList.add(v.getName());
		}
		return vypariNameList;
	}
	
	public Vyapari getVypari(String slipNumber){
		List<Vyapari> vypariList = vypariRepository.findBySlipNumber(slipNumber);
		if(vypariList.size()==0)
			return null;
		return vypariList.get(0);
	}

}
