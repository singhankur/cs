package com.cs.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.Kisan;


@Repository
public interface KisanRepository  extends MongoRepository<Kisan, String>{
	 
	
	List<Kisan> findBySlipNumberLikeOrNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike(String slipNumber, String name , String fatherName, String mobile, String address);
	List<Kisan> findBySlipNumberLike(String slipNumber);
	List<Kisan> findByNameLike(String name);
	List<Kisan> findByFatherNameLike(String fatherName);
	List<Kisan> findByMobileLike(String mobile);
	List<Kisan> findByAddressLike(String address);
	
	//IgnoreCase
	List<Kisan> findByFatherNameLikeIgnoreCase(String fatherName);
	List<Kisan> findByNameLikeIgnoreCase(String name);
	List<Kisan> findBySlipNumber(String slipNumber);
}
