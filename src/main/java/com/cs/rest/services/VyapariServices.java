package com.cs.rest.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cs.mongo.model.Vyapari;
import com.cs.mongo.repository.VypariRepository;

@Service
public class VyapariServices {

	@Autowired
	VypariRepository vypariRepository;
	@Autowired
	KisanService ks;
	
	public String addvypari(Vyapari newVypari) {
		if(newVypari.getSlipNumber().equals("slipNumber"))
			return "Invalid Slip Number";


		if(ks.searchPreviousSlipNumber(newVypari.getSlipNumber()))
			return "Kisan Exist, Try With Differnt Slip Number";
		
		
		Vyapari vypari  = new Vyapari();
		vypari.setCreatedDate(new Date());
		vypari.setLastModifiedDate(new Date());
		vypari.setMaintainedBy(newVypari.getMaintainedBy());
		vypari.setSlipNumber(newVypari.getSlipNumber());
		vypari.setName((newVypari.getName()));
		vypari.setFatherName(newVypari.getFatherName());
		vypari.setLotNumber(newVypari.getLotNumber());
		vypari.setMobile(newVypari.getMobile());
		vypari.setNoOfPacket(newVypari.getNoOfPacket());
		vypari.setAddress(newVypari.getAddress());
		vypari.setTypeOfPotato(newVypari.getTypeOfPotato());
		vypari.setProfileType(newVypari.getProfileType());
		vypari.setDropPricesettled(newVypari.isDropPricesettled());
		vypari.setHavePotato(newVypari.isHavePotato());
		vypariRepository.save(vypari);
		
		return "Vypari Added Successfully";
	}
	
	public Set<String> getAllVypari() {
		List<Vyapari> vypari = vypariRepository.findAll();
		
		Set<String> vypariNameList = new HashSet<String>();
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
