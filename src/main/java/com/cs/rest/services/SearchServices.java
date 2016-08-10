package com.cs.rest.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	Map<String, Kisan> kisanMap;
	Map<String, Vyapari> vypariMap;
	
	
	
	public List<SearchParams> search(SearchParams searchParams) {

		outputlist = new ArrayList<>();
		kisanMap = new HashMap<>();
		vypariMap = new HashMap<>();
		
		if(!StringUtils.isEmpty(searchParams.getSlipNumber())){
			vypariList = vypariRepository.findBySlipNumberLike(searchParams.getSlipNumber());
			kisanList = kisanRepository.findBySlipNumberLike(searchParams.getSlipNumber());
			addSearchOutput();
		}
			
		
		if(!StringUtils.isEmpty(searchParams.getName())){
			vypariList = vypariRepository.findByNameLike(searchParams.getName());
			kisanList = kisanRepository.findByNameLike(searchParams.getName());
			addSearchOutput();
		}
			
		
		if(!StringUtils.isEmpty(searchParams.getFatherName())){
			vypariList = vypariRepository.findByFatherNameLike(searchParams.getFatherName());
			kisanList = kisanRepository.findByFatherNameLike(searchParams.getFatherName());
			addSearchOutput();
		}
			
		
		if(!StringUtils.isEmpty(searchParams.getMobile())){
			vypariList = vypariRepository.findByMobileLike(searchParams.getMobile());
			kisanList = kisanRepository.findByMobileLike(searchParams.getMobile());
			addSearchOutput();
		}
			
		
		if(!StringUtils.isEmpty(searchParams.getAddress())){
			vypariList = vypariRepository.findByAddressLike(searchParams.getAddress());
			kisanList = kisanRepository.findByAddressLike(searchParams.getAddress());
			addSearchOutput();
		}
			
		
		vypariList = vypariRepository.findBySlipNumberLikeOrNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike( searchParams.getSlipNumber(),  searchParams.getName() ,  searchParams.getFatherName(),  searchParams.getMobile(),searchParams.getAddress());
		kisanList = kisanRepository.findBySlipNumberLikeOrNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike( searchParams.getSlipNumber(),  searchParams.getName() ,  searchParams.getFatherName(),  searchParams.getMobile(),searchParams.getAddress());


		vypariFinalResult();kisanFinalResult();
		return outputlist;
	}

	private void vypariFinalResult() {
		for(Entry<String, Vyapari> entry :  vypariMap.entrySet()){
			SearchParams ss = new SearchParams();
			ss.setTypeUser(entry.getValue().getProfileType());
			ss.setAddress(entry.getValue().getAddress());
			ss.setFatherName(entry.getValue().getFatherName());
			ss.setMobile(entry.getValue().getMobile());
			ss.setName(entry.getValue().getName());
			ss.setSlipNumber(entry.getValue().getSlipNumber());
			ss.setNoOfPacket(entry.getValue().getNoOfPacket());
			outputlist.add(ss);
		}
		
	}
	private void kisanFinalResult() {
		for(Entry<String, Kisan> entry :  kisanMap.entrySet()){
			SearchParams ss = new SearchParams();
			ss.setTypeUser(entry.getValue().getProfileType());
			ss.setAddress(entry.getValue().getAddress());
			ss.setFatherName(entry.getValue().getFatherName());
			ss.setMobile(entry.getValue().getMobile());
			ss.setName(entry.getValue().getName());
			ss.setSlipNumber(entry.getValue().getSlipNumber());
			ss.setNoOfPacket(entry.getValue().getNoOfPacket());
			outputlist.add(ss);
		}
		
	}

	private void addSearchOutput() {
		aadVypariListToMap(vypariList);
		aadKisanListToMap(kisanList);
		System.out.println(vypariList);
		System.out.println(kisanList);
		kisanList.clear();
		vypariList.clear();
	}
	
	private void aadVypariListToMap(List<Vyapari> tempvypariList) {
		for(Vyapari v : tempvypariList){
			vypariMap.put(v.getSlipNumber(),v);
		}
	}
	
	private void aadKisanListToMap(List<Kisan> tempKisanList) {
		for(Kisan k : tempKisanList){
			kisanMap.put(k.getSlipNumber(),k);
		}
	
	}

	public List<User> searchProfiles(String slipnumber) {
			 vypariList = vypariRepository.findBySlipNumber(slipnumber);
			 kisanList = kisanRepository.findBySlipNumber(slipnumber);
			 List<User> profiles = new ArrayList<>();
			 profiles.addAll(vypariList);
			 profiles.addAll(kisanList);
			return profiles;
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
