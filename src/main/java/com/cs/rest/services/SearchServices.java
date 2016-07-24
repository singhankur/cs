package com.cs.rest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cs.mongo.model.Kisan;
import com.cs.mongo.model.SearchParams;
import com.cs.mongo.model.User;
import com.cs.mongo.model.Vyapari;
import com.cs.mongo.repository.KisanRepository;
import com.cs.mongo.repository.VypariRepository;

@Service
public class SearchServices {

	@Autowired
	KisanRepository kisanRepository;
	@Autowired
	VypariRepository vypariRepository;
	List<Vyapari> vypariList;
	List<Kisan> kisanList;
	List<SearchParams> outputlist;
	
	public List<SearchParams> search(SearchParams searchParams) {

		outputlist = new ArrayList<>();
		vypariList = vypariRepository.findBySlipNumberLikeOrVypariNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike( searchParams.getSlipNumber(),  searchParams.getName() ,  searchParams.getFatherName(),  searchParams.getMobile(),searchParams.getAddress());
		kisanList = kisanRepository.findBySlipNumberLikeOrKisanNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike( searchParams.getSlipNumber(),  searchParams.getName() ,  searchParams.getFatherName(),  searchParams.getMobile(),searchParams.getAddress());

		for(Kisan k : kisanList){
			SearchParams ss = new SearchParams();
			ss.setTypeUser("kisan");
			ss.setAddress(k.getAddress());
			ss.setFatherName(k.getFatherName());
			ss.setMobile(k.getMobile());
			ss.setName(k.getKisanName());
			ss.setSlipNumber(k.getSlipNumber());
			outputlist.add(ss);
		}
		for(Vyapari v : vypariList){
			SearchParams ss = new SearchParams();
			ss.setTypeUser("Vyapari");
			ss.setAddress(v.getAddress());
			ss.setFatherName(v.getFatherName());
			ss.setMobile(v.getMobile());
			ss.setName(v.getVypariName());
			ss.setSlipNumber(v.getSlipNumber());
			outputlist.add(ss);
		}
		
		
		return outputlist;
	}

	public List<User> searchProfiles(String slipnumber, String session_id) {
		
		if(StringUtils.isEmpty(session_id) || StringUtils.isEmpty(slipnumber))
		return null;
		
		 vypariList = vypariRepository.findBySlipNumber(slipnumber);
		 kisanList = kisanRepository.findBySlipNumber(slipnumber);
		
		 List<User> profiles = new ArrayList<>();
		 profiles.addAll(vypariList);
		 profiles.addAll(kisanList);
		return profiles;
	}

	/*
	 * {"slipNumber":"123","kisanName":"0000000009","fatherName":"69315495TK","lotNumber":"Delhi","pickupPrice":"FLBPL991998","dropPrice":true ,"session_id":"123", "noOfPacket":"40","address":"chummiNagar" , "mobile":"7878787887"}
	 * */
	
}
